package com.example.moviemaster.data.models.response

import com.google.gson.annotations.SerializedName

data class MoviePageResponse(
    @SerializedName("page")
    val page: Long,
    @SerializedName("results")
    val results: List<MovieDetails>,
    @SerializedName("total_pages")
    val totalPages: Long,
    @SerializedName("total_results")
    val totalResults: Long
)