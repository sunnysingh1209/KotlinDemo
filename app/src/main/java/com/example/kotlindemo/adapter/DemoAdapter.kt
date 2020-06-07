package com.example.kotlindemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.R
import com.example.kotlindemo.databinding.CategoryBinding

import com.example.kotlindemo.viewmodels.CategoryViewModel

class DemoAdapter(
    context: Context,
    var list: ArrayList<CategoryViewModel>,
    var action : (CategoryViewModel) -> Unit

) :
    RecyclerView.Adapter<DemoAdapter.MyView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val layoutInflater = LayoutInflater.from(parent.context)
        val categoryBinding: CategoryBinding = DataBindingUtil.inflate(layoutInflater , R.layout.demo_adapter , parent ,false)
        return MyView(categoryBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.bind( list[position])

        holder.binding.mainAdapterLL.setOnClickListener {
            action(list[position])
        }
    }


    class MyView(val binding: CategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(countryModel: CategoryViewModel) {
            binding.category = countryModel
//            signUpAdapter_id?.text = countryViewModel.name
            binding.executePendingBindings()
        }

    }

}