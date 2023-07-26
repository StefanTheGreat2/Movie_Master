package com.example.moviemaster.data.models.entities

import androidx.room.Entity
import com.example.moviemaster.common.constants.DB_NAME


@Entity(primaryKeys = [("id")], tableName = DB_NAME)
data class Movie(
    val backdropPath: String,
    val genreIds: List<Long>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Long,
    val id: Long
)
