package com.example.kotlindemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.R
import com.example.kotlindemo.databinding.MoviesAdapterBinding
import com.example.kotlindemo.esoftware.Movies

class MoviesAdapter(val movieList: List<Movies>) : RecyclerView.Adapter<MoviesAdapter.MyView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val movieAdapterBinding: MoviesAdapterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.movies_adapter,
            parent,
            false
        )

        return MyView(movieAdapterBinding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.moviesAdapterBinding.movie = movieList.get(position)
    }

    inner class MyView(val moviesAdapterBinding: MoviesAdapterBinding) :
        RecyclerView.ViewHolder(moviesAdapterBinding.root)
}