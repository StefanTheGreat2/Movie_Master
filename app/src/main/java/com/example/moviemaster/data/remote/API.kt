package com.example.moviemaster.data.remote

import com.example.moviemaster.common.constants.BASE_POSTER_PATH

object Api {
    @JvmStatic
    fun getPosterPath(posterPath: String?): String {
        return BASE_POSTER_PATH + posterPath
    }


}