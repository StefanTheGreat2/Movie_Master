package com.example.moviemaster.data.remote


import com.example.moviemaster.BuildConfig
import com.example.moviemaster.data.models.response.MovieDetails
import com.example.moviemaster.data.models.response.MoviePageResponse
import com.example.moviemaster.data.models.response.VideoListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("3/movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int = 1,
        @Query("language") lang: String = "en-US",
        @Query("api_key") key: String = BuildConfig.API_KEY
    ): Response<MoviePageResponse>

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1,
        @Query("language") lang: String = "en-US",
        @Query("api_key") key: String = BuildConfig.API_KEY
    ): Response<MoviePageResponse>

    @GET("3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int = 1,
        @Query("language") lang: String = "en-US",
        @Query("api_key") key: String = BuildConfig.API_KEY
    ): Response<MoviePageResponse>

    @GET("3/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int = 1,
        @Query("language") lang: String = "en-US",
        @Query("api_key") key: String = BuildConfig.API_KEY
    ): Response<MoviePageResponse>


    @GET("/3/movie/{movie_id}/videos")
    suspend fun getVideos(@Path("movie_id") id: Long): Response<VideoListResponse>



}