package com.example.kotlindemo.esoftware.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlindemo.esoftware.MoviesViewModel
import com.example.kotlindemo.repository.MoviesRepository

@Suppress("UNCHECKED_CAST")
class MoviesViewModelFactory(private val moviesRepository: MoviesRepository):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(moviesRepository) as T
    }
}