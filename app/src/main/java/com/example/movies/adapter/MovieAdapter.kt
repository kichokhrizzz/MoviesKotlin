package com.example.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.models.Movie


class MovieAdapter(
    private var movies: List<Movie>
) : RecyclerView.Adapter<MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )

    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = movies[position]
        holder.render(item)

    }

    fun updateMovie(movies: List<Movie>){
        this.movies = movies
        notifyDataSetChanged()
    }

}