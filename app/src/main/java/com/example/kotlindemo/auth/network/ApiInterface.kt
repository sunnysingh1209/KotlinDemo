package com.example.kotlindemo.auth.network

import com.example.kotlindemo.model.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {

    @GET("/api/unknown")
    fun doGetListResources(): Call<MultipleResource>

    @GET("marvel")
    fun getHerosList(): Call<List<Hero>>

    @GET("all")
    fun getCountryList(): Call<List<Country>>

    @Headers("Content-Type: application/json")
    @POST("register")
    fun login(
        @Body registerModel: RegisterModel
    ): Observable<LoginModel>
/*
    @POST("/api/users")
    fun createUser(@Body user: User): Call<User>

    @GET("/api/users?")
    fun doGetUserList(@Query("page") page: String): Call<UserList>

    @FormUrlEncoded
    @POST("/api/users?")
    fun doCreateUserWithField(@Field("name") name: String, @Field("job") job: String): Call<UserList>*/

}