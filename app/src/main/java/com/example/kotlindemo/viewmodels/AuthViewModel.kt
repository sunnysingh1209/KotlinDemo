package com.example.kotlindemo.viewmodels

import android.view.View
import androidx.databinding.BaseObservable
import androidx.lifecycle.ViewModel
import com.example.kotlindemo.callbacks.AuthListner
import com.example.kotlindemo.repository.UserRepository

class AuthViewModel : ViewModel() {

    var email: String? = null
    var pass: String? = null
    var authListner: AuthListner? = null

    fun onLoginBtnClick(view: View) {
        authListner?.onStarted()
        if (email.isNullOrEmpty() || pass.isNullOrEmpty()) {
            authListner?.onFailure("Failure")
            return
        }

        val loginResponse = UserRepository().userLogin("Sunny", email!!, pass!!)
        authListner?.onSuccess(loginResponse)

    }
}