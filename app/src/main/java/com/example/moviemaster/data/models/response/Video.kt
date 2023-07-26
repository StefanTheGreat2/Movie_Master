package com.example.moviemaster.data.models.response

import javax.annotation.concurrent.Immutable

@Immutable
data class Video(
    val id: String,
    val name: String,
    val site: String,
    val key: String,
    val size: Int,
    val type: String
)