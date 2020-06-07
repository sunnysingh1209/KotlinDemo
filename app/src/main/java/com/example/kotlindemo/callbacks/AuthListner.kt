package com.example.kotlindemo.callbacks

import androidx.lifecycle.LiveData

interface AuthListner {
    fun onStarted()
    fun onSuccess(loginResponse: LiveData<String>)
    fun onFailure(msg : String)
}