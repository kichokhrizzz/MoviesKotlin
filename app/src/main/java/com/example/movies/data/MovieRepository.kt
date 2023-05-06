package com.example.movies.data

import com.example.movies.data.database.dao.MovieDao
import com.example.movies.data.model.MovieModel
import com.example.movies.services.MovieAPIService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val api: MovieAPIService,private val movieDao : MovieDao){


    suspend fun getAllMoviesFromDatabase(){

    }
}