package com.example.kotlindemo.auth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.R
import com.example.kotlindemo.auth.network.APIClient
import com.example.kotlindemo.auth.network.ApiInterface
import com.example.kotlindemo.model.Hero
import com.example.kotlindemo.model.MultipleResource
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpActivity : AppCompatActivity() {

    var apiInterface: ApiInterface? = null
    var signUpRecView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        signUpRecView = findViewById(R.id.signUpRecView)

        SignInBtn.setOnClickListener {
            //            getAllMultipleRes()
            getHeroList()
        }

        apiInterface = APIClient.getClient().create(ApiInterface::class.java)

    }

    private fun getHeroList() {
        var call = apiInterface?.getHerosList()
        call?.enqueue(object : Callback<List<Hero>> {
            override fun onFailure(call: Call<List<Hero>>, t: Throwable) {
                Toast.makeText(this@SignUpActivity, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Hero>>, response: Response<List<Hero>>) {
                var displayResponse = ""

                val resource = response.body()

                for (data in resource!!) {
                    displayResponse += "${data?.name}" + "\n"
                    displayResponse += "${data?.realname}" + "\n"
                    displayResponse += "${data?.firstappearance}" + "\n"
                    displayResponse += "${data?.imageurl}" + "\n"
                    displayResponse += "${data?.publisher}" + "\n"
                    displayResponse += "${data?.team}" + "\n"
                    displayResponse += "${data?.bio}" + "\n"
                }

                signUpTv.text = displayResponse
            }


        })

    }

    private fun getAllMultipleRes() {
        val call = apiInterface?.doGetListResources()
        call?.enqueue(object : Callback<MultipleResource> {
            override fun onResponse(
                call: Call<MultipleResource>,
                response: Response<MultipleResource>
            ) {

                var displayResponse = ""

                val resource = response.body()
                val text = resource?.page
                val total = resource?.total
                val totalPages = resource?.totalPages
                val datumList = resource?.data
                val add = resource?.ad

                displayResponse += "$text Page\n" + total + " Total\n" + totalPages + " Total Pages\n"

                for (datum in datumList!!) {
                    displayResponse += "${datum.id}  " + datum.name + " " + datum.pantoneValue + " " + datum.year + "\n"
                }

                displayResponse += add?.company

                signUpTv.text = displayResponse

            }

            override fun onFailure(call: Call<MultipleResource>, t: Throwable) {
                call.cancel()
                Toast.makeText(this@SignUpActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })

    }
}
