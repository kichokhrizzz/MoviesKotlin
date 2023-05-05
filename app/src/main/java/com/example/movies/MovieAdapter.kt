package com.example.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.models.Movie
import org.w3c.dom.Text

class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view){
    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

    val movieTitle = view.findViewById<TextView>(R.id.movie_title)
    val movieRate = view.findViewById<TextView>(R.id.movie_vote_average)
    val moviePoster = view.findViewById<ImageView>(R.id.movie_poster)
    fun bindMovie(movie : Movie){
        movieTitle.text= movie.title
        movieRate.text = movie.vote

        Glide.with(itemView).load(IMAGE_BASE + movie.poster).into(moviePoster)
    }
}


class MovieAdapter(
    private val movies : List<Movie>
) : RecyclerView.Adapter<MovieViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies.get(position))
    }
}