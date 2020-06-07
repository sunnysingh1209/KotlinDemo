package com.example.kotlindemo.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("capital")
    var capital: String? = null,
    @SerializedName("currencies")
    var currencies: List<Currencies>? = null
) {

    data class Currencies(
        @SerializedName("code")
        var code: String? = null,

        @SerializedName("name")
        var name: String? = null,

        @SerializedName("symbol")
        var symbol: String? = null
    ) {

    }
}