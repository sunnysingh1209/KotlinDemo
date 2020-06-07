package com.example.kotlindemo.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.R
import com.example.kotlindemo.adapter.DemoAdapter
import com.example.kotlindemo.auth.network.MyClass
import com.example.kotlindemo.esoftware.MoviesActivity
import com.example.kotlindemo.viewmodels.CategoryViewModel

class DemoActivity : AppCompatActivity() {

    var demoRecView: RecyclerView? = null
    var adapter: DemoAdapter? = null
    var categoryViewModelList = ArrayList<CategoryViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        demoRecView = findViewById(R.id.demoRecView)
        MyClass.setContext(this@DemoActivity)

        var categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
        categoryViewModel.getCategoryList().observe(this, Observer {
            categoryViewModelList.clear()
            categoryViewModelList.addAll(it)
            adapter = DemoAdapter(this@DemoActivity, categoryViewModelList, { pos -> show(pos) })
            demoRecView?.layoutManager = LinearLayoutManager(this@DemoActivity)
            demoRecView?.adapter = adapter
        })
    }

    fun show(categoryViewModel: CategoryViewModel) {
        Toast.makeText(this, categoryViewModel.title, Toast.LENGTH_SHORT).show()
        if (categoryViewModel.chBox == 0) {
            categoryViewModel.chBox = 1
        } else {
            categoryViewModel.chBox = 0
        }
        adapter?.notifyDataSetChanged()
        startActivity(Intent(this, MoviesActivity::class.java))

    }
}
