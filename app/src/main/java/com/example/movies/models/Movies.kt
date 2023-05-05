package com.example.movies.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("poster_path")
    val poster: String?,

    @SerializedName("vote_average")
    val vote: String?,

    @SerializedName("backdrop_path")
    val  backdrop: String?,

    @SerializedName("overview")
    val overview: String?,
) : Parcelable {
    constructor() : this(1, "", "", "", "", "")
}