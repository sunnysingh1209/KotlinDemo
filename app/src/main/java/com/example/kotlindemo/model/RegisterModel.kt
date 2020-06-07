package com.example.kotlindemo.model

import com.google.gson.annotations.SerializedName

data class RegisterModel(
    @SerializedName("type") var type: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("password") var password: String? = null,
    @SerializedName("panelType") var paneltype: String? = null
) {
}