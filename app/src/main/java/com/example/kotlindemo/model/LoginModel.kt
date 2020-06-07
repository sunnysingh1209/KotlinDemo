package com.example.kotlindemo.model

import com.google.gson.annotations.SerializedName

data class LoginModel(
    @SerializedName("data")
    var data: Data? = null,

    @SerializedName("success")
    var success: Success? = null

) {

    data class Success(
        @SerializedName("token")
        var token: String? = null
    )

    data class Data(
        @SerializedName("name")
        var name: String? = null,

        @SerializedName("email")
        var email: String? = null,

        @SerializedName("password")
        var password: String? = null,

        @SerializedName("panelType")
        var panelType: String? = null,

        @SerializedName("email_verified_at")
        var email_verified_at: String? = null
    )
}