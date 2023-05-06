package com.example.movies.adapter

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.MovieDetailFragment
import com.example.movies.R
import com.example.movies.databinding.ItemMovieBinding
import com.example.movies.data.model.MovieModel

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemMovieBinding.bind(view)

    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

    val movieTitle = binding.movieTitle
    val movieRate = binding.movieVoteAverage
    val moviePoster = binding.moviePoster

    fun render(movie: MovieModel) {
        movieTitle.text = movie.title
        movieRate.text = movie.vote

        Glide.with(moviePoster).load(IMAGE_BASE + movie.poster).into(moviePoster)

        itemView.setOnClickListener{
            val fragment = MovieDetailFragment()
            val bundle = Bundle()
            bundle.putInt("movieId", movie.id!!) // Pasamos el id de la película como argumento al Fragment
            fragment.arguments = bundle
            val transaction = (itemView.context as AppCompatActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }
    }


}
