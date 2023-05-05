package com.example.movies.services

import com.example.movies.models.Movie
import com.example.movies.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieAPIInterface {

    @GET("movie/top_rated?api_key=1fd9248cbe4d12cbf6402b04ddd81702")
    fun getTopMovieList(): Call<MovieResponse>

    @GET("movie/now_playing?api_key=1fd9248cbe4d12cbf6402b04ddd81702")
    fun getCarteleraMovieList(): Call<MovieResponse>

    @GET("movie/{id}?api_key=1fd9248cbe4d12cbf6402b04ddd81702")
    fun getMovieById(@Path("id") id: Int): Call<Movie>

}
