package com.example.kotlindemo.repository

import com.example.kotlindemo.esoftware.MoviesApi
import com.example.kotlindemo.esoftware.SafeApiRequest

class MoviesRepository(private val api: MoviesApi) : SafeApiRequest() {

    suspend fun getMovies() = apiRequest {
        api.getMovies()
    }

}