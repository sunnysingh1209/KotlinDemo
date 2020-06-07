package com.example.kotlindemo.esoftware

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlindemo.repository.MoviesRepository
import kotlinx.coroutines.Job

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private lateinit var job: Job
    var mutableMovieList = MutableLiveData<List<Movies>>()
    var getMutableMovieList: LiveData<List<Movies>>? = null
        get() = mutableMovieList

    /*
        suspend fun getMovies() {
            val response = moviesRepository.getMovies()
            mutableMovieList.value = response
        }*/
    fun getMovies() {
        job = Coroutines.ioThenMain({ moviesRepository.getMovies() }, {
            mutableMovieList.value = it
        })
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}
