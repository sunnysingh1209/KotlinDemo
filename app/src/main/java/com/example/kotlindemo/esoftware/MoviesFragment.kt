package com.example.kotlindemo.esoftware

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlindemo.R
import com.example.kotlindemo.adapter.MoviesAdapter
import com.example.kotlindemo.esoftware.factory.MoviesViewModelFactory
import com.example.kotlindemo.repository.MoviesRepository
import kotlinx.android.synthetic.main.movies_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesFragment : Fragment() {

    private lateinit var viewModel: MoviesViewModel
    private lateinit var factory: MoviesViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var moviesRepository = MoviesRepository(MoviesApi())
        factory = MoviesViewModelFactory(moviesRepository)

        viewModel = ViewModelProviders.of(this, factory).get(MoviesViewModel::class.java)

       /* CoroutineScope(Dispatchers.Main).launch {
            viewModel.getMovies()
        }*/

        viewModel.getMovies()
        viewModel.getMutableMovieList?.observe(viewLifecycleOwner, Observer {
            moviesRecView.layoutManager = LinearLayoutManager(requireContext())
            moviesRecView.setHasFixedSize(true)
            moviesRecView.adapter = MoviesAdapter(it)
        })
    }

}
