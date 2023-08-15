package com.example.moviemaster.data.repository

import androidx.annotation.WorkerThread
import com.example.moviemaster.common.resource.Resource
import com.example.moviemaster.data.local.MovieDao
import com.example.moviemaster.data.models.entities.Movie
import com.example.moviemaster.data.models.response.BaseResponse
import com.example.moviemaster.data.models.response.MoviePageResponse
import com.example.moviemaster.data.remote.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService,
    private val movieDao: MovieDao
) : BaseResponse() {
    @WorkerThread
    fun getNowPlaying(page: Int): Flow<Resource<MoviePageResponse>> = flow {
        emit(Resource.Loading())
        emit(safeApiCall { movieService.getNowPlayingMovies(page) })
    }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun getPopularMovies(page: Int): Flow<Resource<MoviePageResponse>> = flow {
        emit(Resource.Loading())
        emit(safeApiCall { movieService.getPopularMovies(page) })
    }.flowOn(Dispatchers.IO)
    @WorkerThread
    fun getTopRatedMovies(page: Int): Flow<Resource<MoviePageResponse>> = flow {
        emit(Resource.Loading())
        emit(safeApiCall { movieService.getTopRatedMovies(page) })
    }.flowOn(Dispatchers.IO)



    @WorkerThread
    fun getUpcomingMovies(page: Int): Flow<Resource<MoviePageResponse>> = flow {
        emit(Resource.Loading())
        emit(safeApiCall { movieService.getUpcomingMovies(page) })
    }.flowOn(Dispatchers.IO)

    fun loadAllMovies(): Flow<List<Movie>> = movieDao.getAllMovies().flowOn(Dispatchers.IO)

    fun saveMovie(movie: Movie) = movieDao.addMovie(movie)

    fun deleteMovie(movie: Movie) = movieDao.deleteMovie(movie)

    fun getFavoriteMovie(id: Long) = flow {
        val movie = movieDao.getMovie(id)
        emit(movie)
    }.flowOn(Dispatchers.IO)
}