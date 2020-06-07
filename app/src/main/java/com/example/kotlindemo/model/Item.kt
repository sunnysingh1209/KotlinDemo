package com.example.kotlindemo.model

import android.view.View
import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.kotlindemo.BR

data class Item(
    private var _name: String,
    private var _email: String
) : BaseObservable() {

    var name: String
        @Bindable get() = _name
        set(value) {
            _name = value
            notifyPropertyChanged(BR.name)
        }


    var email: String
        @Bindable get() = _email
        set(value) {
            _email = value
            notifyPropertyChanged(BR.email)
        }

    companion object {
        @JvmStatic
//        @BindingAdapter("android:src")
        @BindingAdapter("profileImage")
        fun loadImage(view: ImageView, imageUrl: String) {
            Glide.with(view.getContext())
                .load(imageUrl)
                .into(view)
        }
    }

    fun onButtonClick(view: View) {
        email = "karan@gmail,co"
        name = "Karan"
    }
}