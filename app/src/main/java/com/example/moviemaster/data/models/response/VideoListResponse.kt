package com.example.moviemaster.data.models.response

import javax.annotation.concurrent.Immutable

@Immutable
data class VideoListResponse(
    val id: Int,
    val results: List<Video>
)