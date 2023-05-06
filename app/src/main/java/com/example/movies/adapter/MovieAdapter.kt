package com.example.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R

import com.example.movies.data.model.MovieModel


class MovieAdapter(
    private var movies: List<MovieModel>,
    private val onMovieClickListener: OnMovieClickListener

) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.render(movie)

        holder.itemView.setOnClickListener {
            onMovieClickListener.onMovieClick(movie.id!!)
        }
    }

    fun updateMovie(movies: List<MovieModel>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    interface OnMovieClickListener {
        fun onMovieClick(movieId: Int)
    }
}
