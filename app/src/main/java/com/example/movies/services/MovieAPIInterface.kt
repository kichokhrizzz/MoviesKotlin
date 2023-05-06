package com.example.movies.services

import com.example.movies.data.model.MovieModel
import com.example.movies.data.model.MovieProvider
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieAPIInterface {

    @GET("movie/top_rated?api_key=1fd9248cbe4d12cbf6402b04ddd81702")
    fun getTopMovieList(): Call<MovieProvider>

    @GET("movie/now_playing?api_key=1fd9248cbe4d12cbf6402b04ddd81702")
    fun getCarteleraMovieList(): Call<MovieProvider>

    @GET("movie/{id}?api_key=1fd9248cbe4d12cbf6402b04ddd81702")
    fun getMovieById(@Path("id") id: Int): Call<MovieModel>

}
