package com.example.movies.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.databinding.ItemMovieBinding
import com.example.movies.models.Movie

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemMovieBinding.bind(view)

    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

    val movieTitle = binding.movieTitle
    val movieRate = binding.movieVoteAverage
    val moviePoster = binding.moviePoster

    fun render(movie: Movie) {
        movieTitle.text = movie.title
        movieRate.text = movie.vote

        Glide.with(moviePoster).load(IMAGE_BASE + movie.poster).into(moviePoster)

        itemView.setOnClickListener{
            Toast.makeText(
                moviePoster.context,
                movie.title,
                Toast.LENGTH_LONG
            ).show()
        }
    }


}
