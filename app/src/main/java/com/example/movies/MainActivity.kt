package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.adapter.MovieAdapter
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.data.model.MovieModel
import com.example.movies.data.model.MovieProvider
import com.example.movies.services.MovieAPIInterface
import com.example.movies.services.MovieAPIService
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), MovieAdapter.OnMovieClickListener {

    private lateinit var binding: ActivityMainBinding
    private var topMoviesMutableList: MutableList<MovieModel> = mutableListOf()
    private var carteleraMoviesMutableList: MutableList<MovieModel> = mutableListOf()
    private lateinit var topMoviesAdapter: MovieAdapter
    private lateinit var carteleraMoviesAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        val screenSplash = installSplashScreen()


        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Thread.sleep(3000)

        screenSplash.setKeepOnScreenCondition{
            false
        }


        binding.etFilter.addTextChangedListener { userFilter ->
            val topMoviesFiltered = topMoviesMutableList.filter {
                    movie -> movie.title!!.toLowerCase().contains(userFilter.toString().toLowerCase())
            }
            topMoviesAdapter.updateMovie(topMoviesFiltered)

            val carteleraMoviesFiltered = carteleraMoviesMutableList.filter {
                    movie -> movie.title!!.toLowerCase().contains(userFilter.toString().toLowerCase())
            }
            carteleraMoviesAdapter.updateMovie(carteleraMoviesFiltered)
        }

        initRecyclerViews()

    }

    private fun initRecyclerViews() {
        topMoviesAdapter = MovieAdapter(movies = topMoviesMutableList, onMovieClickListener = this)
        carteleraMoviesAdapter = MovieAdapter(movies = carteleraMoviesMutableList, onMovieClickListener = this)

        binding.rvTopMoviesList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = topMoviesAdapter
        }

        binding.rvCarteleraMoviesList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = carteleraMoviesAdapter
        }

        getTopMoviesData { movies: List<MovieModel> ->
            topMoviesMutableList.clear()
            topMoviesMutableList.addAll(movies)
            topMoviesAdapter.notifyDataSetChanged()
        }

        getCarteleraMoviesData { movies: List<MovieModel> ->
            carteleraMoviesMutableList.clear()
            carteleraMoviesMutableList.addAll(movies)
            carteleraMoviesAdapter.notifyDataSetChanged()
        }
    }

    private fun getTopMoviesData(callback: (List<MovieModel>) -> Unit) {
        val apiService = MovieAPIService.getInstance().create(MovieAPIInterface::class.java)
        apiService.getTopMovieList().enqueue(object : retrofit2.Callback<MovieProvider> {
            override fun onResponse(call: Call<MovieProvider>, response: Response<MovieProvider>){

                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieProvider>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun getCarteleraMoviesData(callback: (List<MovieModel>) -> Unit) {
        val apiService = MovieAPIService.getInstance().create(MovieAPIInterface::class.java)
        apiService.getCarteleraMovieList().enqueue(object : retrofit2.Callback<MovieProvider> {
            override fun onResponse(call: Call<MovieProvider>, response: Response<MovieProvider>) {
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieProvider>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onMovieClick(movieId: Int) {
        val movieDetailFragment = MovieDetailFragment()
        val bundle = Bundle()
        bundle.putInt("movieId", movieId)
        movieDetailFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, movieDetailFragment)
            .addToBackStack(null)
            .commit()

    }
}
