package com.example.kotlindemo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountryViewModel : ViewModel() {
    var countryModelList = MutableLiveData<ArrayList<CountryModel>>()
    var countryList = ArrayList<CountryModel>()

    var name: String = ""

    fun getCountryList(): LiveData<ArrayList<CountryModel>> {

        countryList.add(CountryModel("Sunny", "https://api.androidhive.info/images/nature/1.jpg"))
        countryList.add(CountryModel("Arun", "https://api.androidhive.info/images/nature/1.jpg"))
        countryList.add(CountryModel("Sun", "https://api.androidhive.info/images/nature/1.jpg"))
        countryList.add(CountryModel("Sunny", "https://api.androidhive.info/images/nature/1.jpg"))
        countryList.add(CountryModel("Sunny", "https://api.androidhive.info/images/nature/1.jpg"))

        countryModelList.value = countryList

        return countryModelList
    }

}