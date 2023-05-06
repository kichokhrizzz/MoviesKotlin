package com.example.movies.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "poster") val poster: String,
    @ColumnInfo(name = "vote") val vote: String,
    @ColumnInfo(name = "backdrop") val backdrop: String,
    @ColumnInfo(name = "overview") val overview: String
)