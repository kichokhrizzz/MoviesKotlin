package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler.Callback
import android.os.ProxyFileDescriptorCallback
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.models.Movie
import com.example.movies.models.MovieResponse
import com.example.movies.services.MovieAPIInterface
import com.example.movies.services.MovieAPIService
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topRecyler = findViewById<RecyclerView>(R.id.rv_top_movies_list)
        val carteleraRecycler = findViewById<RecyclerView>(R.id.rv_cartelera_movies_list)

        topRecyler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        topRecyler.setHasFixedSize(true)
        getMovieData {
            movies: List<Movie> ->
            topRecyler.adapter = MovieAdapter(movies)
        }

        carteleraRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        carteleraRecycler.setHasFixedSize(true)

        getCarteleaMovieData {
            movies: List<Movie> ->
            carteleraRecycler.adapter = MovieAdapter(movies)
        }

    }

    private fun getMovieData(callback: (List<Movie>) -> Unit){
        val apiService = MovieAPIService.getInstance().create(MovieAPIInterface::class.java)
        apiService.getTopMovieList().enqueue(object : retrofit2.Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun getCarteleaMovieData(callback: (List<Movie>) -> Unit){
        val apiService = MovieAPIService.getInstance().create(MovieAPIInterface::class.java)
        apiService.getCarteleraMovieList().enqueue(object  : retrofit2.Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}