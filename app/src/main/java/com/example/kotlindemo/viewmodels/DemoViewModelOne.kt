package com.example.kotlindemo.viewmodels

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.kotlindemo.BR
import com.example.kotlindemo.model.DemoModelOne
import java.util.*

class DemoViewModelOne(val demoModelOne: DemoModelOne) : Observer, BaseObservable() {
    init {
        demoModelOne.addObserver(this)
    }

    override fun update(p0: Observable?, p1: Any?) {
        if (p1 is String) {
            if (p1 == "name") {
                notifyPropertyChanged(BR.name)
            } else if (p1 == "email") {
                notifyPropertyChanged(BR.email)
            }
        }
    }

    val name: String
        @Bindable get() {
            return demoModelOne.name
        }
    val email: String
        @Bindable get() {
            return demoModelOne.email
        }

    fun onButtonClick(view: View) {
        demoModelOne.email = "Jaran@gmail,co"
        demoModelOne.name = "Karan"
    }

}