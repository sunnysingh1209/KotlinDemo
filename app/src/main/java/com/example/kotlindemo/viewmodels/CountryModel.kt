package com.example.kotlindemo.viewmodels

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

data class CountryModel(var name: String = "", var imageUrl: String = "") {

    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, imageUrl: String) {
        Glide.with(view.getContext())
            .load(imageUrl)
            .into(view)
    }
}