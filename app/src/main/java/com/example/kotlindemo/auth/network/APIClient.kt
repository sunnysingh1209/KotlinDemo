package com.example.kotlindemo.auth.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    companion object {
        var retrofit: Retrofit? = null
        fun getClient(): Retrofit {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            retrofit = Retrofit.Builder()
//                .baseUrl("https://reqres.in")
//                .baseUrl("https://simplifiedcoding.net/demos/")
//                .baseUrl("http://api.simplifiedcoding.in/course-apis/recyclerview/movies") // important
                .baseUrl("https://restcountries.eu/rest/v2/")

//                .baseUrl("https://burnapp.appspot.com/api/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit as Retrofit
        }
    }

}