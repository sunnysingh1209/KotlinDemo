package com.example.kotlindemo.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kotlindemo.DemoActivityOne
import com.example.kotlindemo.R
import com.example.kotlindemo.auth.network.MyClass
import com.example.kotlindemo.callbacks.AuthListner
import com.example.kotlindemo.databinding.ActivityLoggedInBinding
import com.example.kotlindemo.viewmodels.AuthViewModel
import kotlinx.android.synthetic.main.activity_logged_in.*

class LoggedInActivity : AppCompatActivity(), AuthListner {
    override fun onStarted() {
        Toast.makeText(this@LoggedInActivity, "Started", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loginResponse.observe(this, Observer {
            Toast.makeText(this, it + "", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, SignUpActivity::class.java))
        })
    }

    override fun onFailure(msg: String) {
        Toast.makeText(this@LoggedInActivity, msg, Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, DemoActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)

        MyClass.setContext(this@LoggedInActivity)
        val binding: ActivityLoggedInBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_logged_in)
        var viewModel = ViewModelProviders.of(this@LoggedInActivity).get(AuthViewModel::class.java)
        binding.authViewModel = viewModel
        viewModel.authListner = this

        loggedSignUpBtn.setOnClickListener {
            startActivity(Intent(this, DemoActivityOne::class.java))
        }
    }
}