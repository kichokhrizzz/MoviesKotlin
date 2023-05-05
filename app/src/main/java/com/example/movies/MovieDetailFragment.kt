package com.example.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.movies.databinding.FragmentMovieDetailBinding
import com.example.movies.models.Movie
import com.example.movies.services.MovieAPIInterface
import com.example.movies.services.MovieAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private var movieId: Int = 0
    private lateinit var apiService: MovieAPIInterface

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        apiService = MovieAPIService.getInstance().create(MovieAPIInterface::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        movieId = args?.getInt("movieId", 0) ?: 0
        if (movieId != 0) {
            getMovieById(movieId)
        }
    }

    private fun getMovieById(id: Int) {
        apiService.getMovieById(id).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful && response.body() != null) {
                    val movie = response.body()
                    updateViews(movie)
                } else {
                    Toast.makeText(context, "Error al obtener la película.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Toast.makeText(context, "Error al obtener la película.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateViews(movie: Movie?) {
        if (movie != null) {
            binding.movieTitleTextView.text = movie.title
            binding.movieOverviewTextView.text = movie.overview
            binding.movieVoteAverage.text = movie.vote

            Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500${movie.backdrop}")
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(binding.moviePosterImageView)
        }
    }
}
