package com.example.movies.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MovieProvider(
    @SerializedName("results")
    val movies: List<MovieModel>
) : Parcelable {
    constructor() : this(mutableListOf())
}