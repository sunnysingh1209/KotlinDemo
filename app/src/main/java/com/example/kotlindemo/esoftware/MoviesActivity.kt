package com.example.kotlindemo.esoftware

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
/*
        val repository = MoviesRepository(MoviesApi())
        GlobalScope.launch(Dispatchers.Main) {
            val movies = repository.getMovies()
            Toast.makeText(this@MoviesActivity, movies.toString(), Toast.LENGTH_SHORT).show()
        }*/


    }
}
