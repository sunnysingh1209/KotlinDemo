package com.example.kotlindemo.repository

import android.util.Log
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.kotlindemo.auth.network.MyClass
import com.example.kotlindemo.model.Catergory
import com.example.kotlindemo.viewmodels.CategoryViewModel
import org.json.JSONArray


class CategoryRepository {

    fun getData(): ArrayList<CategoryViewModel> {

        var loginRes = ArrayList<CategoryViewModel>()

        val request = StringRequest(Request.Method.GET, "https://restcountries.eu/rest/v2/all", object : Response.Listener<String> {
            override fun onResponse(response: String) {
                Log.e("Response", response + "")
                try {

                    val jsonArr = JSONArray(response)
                    for (i in 0 until jsonArr.length()) {
                        var jsonObject = jsonArr.getJSONObject(i)

                        loginRes.add(
                            CategoryViewModel(
                                Catergory(
                                    jsonObject.getString("name"),
                                    jsonObject.getString("name")
                                )
                            )
                        )
                    }

                } catch (e: Exception) {

                }

            }
        }, object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError) {
                Log.e("Response", error.message + "")
            }
        })

        request.retryPolicy = DefaultRetryPolicy(
            30000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        val requestQueue = Volley.newRequestQueue(MyClass.getContext())
        requestQueue.add(request)

        return loginRes
    }


}