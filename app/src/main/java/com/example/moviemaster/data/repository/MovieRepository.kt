package com.example.moviemaster.data.repository

import com.example.moviemaster.data.local.MovieDao
import com.example.moviemaster.data.models.entities.Movie
import com.example.moviemaster.data.models.response.MoviePage
import com.example.moviemaster.data.remote.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val moviesApi: MovieService,
    private val movieDao: MovieDao
) {

    suspend fun getNowPlaying(currentPage: Int): Response<MoviePage> {
        return moviesApi.getNowPlayingMovies(currentPage)
    }

    suspend fun getPopularMovies(currentPage: Int): Response<MoviePage> {
        return moviesApi.getPopularMovies(currentPage)
    }

    suspend fun getTopRatedMovies(currentPage: Int): Response<MoviePage> {
        return moviesApi.getTopRatedMovies(currentPage)
    }

    suspend fun getUpComingMovies(currentPage: Int): Response<MoviePage> {
        return moviesApi.getUpcomingMovies(currentPage)
    }


    fun loadAllMovies(): Flow<List<Movie>> = movieDao.getAllMovies().flowOn(Dispatchers.IO)

    fun saveMovie(movie: Movie) = movieDao.addMovie(movie)

    fun deleteMovie(movie: Movie) = movieDao.deleteMovie(movie)

    fun getFavoriteMovie(id: Long) = flow {
        val movie = movieDao.getMovie(id)
        emit(movie)
    }.flowOn(Dispatchers.IO)
}