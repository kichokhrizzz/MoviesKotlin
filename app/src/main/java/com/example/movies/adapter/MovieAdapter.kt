package com.example.movies.adapter

import com.example.movies.adapter.MovieViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.models.Movie
import org.w3c.dom.Text


class MovieAdapter(
    private var movies: List<Movie>,
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

    fun updateMovie(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    interface OnMovieClickListener {
        fun onMovieClick(movieId: Int)
    }
}
