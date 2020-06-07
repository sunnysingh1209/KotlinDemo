package com.example.kotlindemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kotlindemo.databinding.ActivityDemoOneBinding
import com.example.kotlindemo.model.DemoModelOne
import com.example.kotlindemo.model.Item
import com.example.kotlindemo.viewmodels.DemoViewModelOne

class DemoActivityOne : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_one)

        var demoModelOne = DemoModelOne()
        demoModelOne.name = "Sunny"
        demoModelOne.email = "Sunny@gmail.com"

        var demoViewModelOne = DemoViewModelOne(demoModelOne)
        var item = Item("Sunny", "Sunny@gmail.com")
        var binding: ActivityDemoOneBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_demo_one)
        binding.demoOne = item
        binding.imageUrl = "https://www.simplifiedcoding.net//demos//marvel//captainamerica.jpg"
//        binding.setVariable(BR.demoOne, demoViewModelOne)


    }
}
