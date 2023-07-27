package com.example.moviemaster.ui.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemaster.common.resource.Resource
import com.example.moviemaster.data.models.entities.Movie
import com.example.moviemaster.data.models.response.MoviePage
import com.example.moviemaster.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val moviesRepository: MovieRepository
) : ViewModel() {
    val movies: MutableLiveData<Resource<MoviePage>> = MutableLiveData()
    var categoryState = "Now Playing"
    var page = 1

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getMovies(moviesRepository.getNowPlaying(1))
        }


    }

    private fun getMovies(response: Response<MoviePage>) {
        safeMoviesCall(movies, response)
    }

    private fun safeMoviesCall(
        moviesData: MutableLiveData<Resource<MoviePage>>,
        response: Response<MoviePage>
    ) {
        moviesData.postValue(Resource.Loading())
        try {
            moviesData.postValue(handleResp(response))


        } catch (ex: Exception) {
            when (ex) {
                is IOException -> moviesData.postValue(Resource.Error("Network Failure"))
                else -> moviesData.postValue(Resource.Error("Conversion Error"))
            }
        }
    }


    private fun handleResp(response: Response<MoviePage>): Resource<MoviePage> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    fun nowPlaying(page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            getMovies(moviesRepository.getNowPlaying(page))
        }
    }

    fun popular(page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            getMovies(moviesRepository.getPopularMovies(page))
        }
    }

    fun topRated(page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            getMovies(moviesRepository.getTopRatedMovies(page))
        }
    }

    fun upcoming(page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            getMovies(moviesRepository.getUpComingMovies(page))
        }
    }

    fun saveMovieToFavorites(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            moviesRepository.saveMovie(movie)
        }
    }

    fun deleteMovieFromFavorites(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            moviesRepository.deleteMovie(movie)
        }
    }


    fun getFavoriteMovies(): Flow<List<Movie>> {
        return moviesRepository.loadAllMovies()
    }

    fun getFavoriteMovieById(id: Long) = moviesRepository.getFavoriteMovie(id)

    fun nextPage() {
        if (page < 100) {

            when {
                categoryState === "Now Playing" -> {
                    page++
                    nowPlaying(page)
                }
                categoryState === "Popular" -> {
                    page++
                    popular(page)
                }
                categoryState === "Upcoming" -> {
                    page++
                    upcoming(page)
                }
                categoryState === "TopRated" -> {
                    page++
                    topRated(page)
                }
            }

        }
    }

    fun previousPage() {
        if (page > 1) {
            when {
                categoryState === "Now Playing" -> {
                    page--
                    nowPlaying(page)
                }
                categoryState === "Popular" -> {
                    page--
                    popular(page)
                }
                categoryState === "Upcoming" -> {
                    page--
                    upcoming(page)
                }
                categoryState === "TopRated" -> {
                    page--
                    topRated(page)
                }
            }

        }
    }
}




