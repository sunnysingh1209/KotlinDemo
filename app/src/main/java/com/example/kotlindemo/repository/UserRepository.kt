package com.example.kotlindemo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlindemo.auth.network.APIClient
import com.example.kotlindemo.auth.network.ApiInterface
import com.example.kotlindemo.model.RegisterModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class UserRepository {

    fun userLogin(name: String, email: String, pass: String): LiveData<String> {

        val loginResponse = MutableLiveData<String>()
/*

        val json = JSONObject()
        try {
            json.put("type", "user")
            json.put("name", name)
            json.put("panelType", "3")
            json.put("email", email)
            json.put("password", pass)

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val jsonObjectRequest =
            JsonObjectRequest(
                Request.Method.POST, "https://burnapp.appspot.com/api/register", json,
                object : Response.Listener<JSONObject> {
                    override fun onResponse(response: JSONObject) {
                        loginResponse.value = response.toString()
                    }
                }, object : Response.ErrorListener {
                    override fun onErrorResponse(error: VolleyError) {
                        loginResponse.value = error.message
                    }
                })

        jsonObjectRequest.retryPolicy = DefaultRetryPolicy(
            30000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        val requestQueue = Volley.newRequestQueue(MyClass.getContext())
        requestQueue.add(jsonObjectRequest)
*/


        var apiInterface = APIClient.getClient().create(ApiInterface::class.java)
        var registerModel = RegisterModel("user", "Sunny", email, pass, "3")
        var observable = apiInterface.login(registerModel)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ loginRes ->

                /** loginResponse is response data class*/

                loginResponse.value = loginRes.data?.email
            }, { error ->
                loginResponse.value = error.message
            }
            )
        return loginResponse
    }
}