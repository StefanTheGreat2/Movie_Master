package com.example.moviemaster.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemaster.common.resource.Resource
import com.example.moviemaster.data.models.entities.Movie
import com.example.moviemaster.data.models.response.MoviePageResponse
import com.example.moviemaster.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val moviesRepository: MovieRepository
) : ViewModel() {
    private val movies = MutableStateFlow<Resource<MoviePageResponse>?>(Resource.Loading())
    val moviesState: StateFlow<Resource<MoviePageResponse>?> = movies
    private var categoryState = "Now Playing"
    private var page = 1

    init {
        viewModelScope.launch(Dispatchers.IO) {
            nowPlaying()
        }
    }

    fun nowPlaying(page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {

            moviesRepository.getNowPlaying(page).collect {
                movies.value = it
            }
        }
    }

    fun popular(page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {

            moviesRepository.getPopularMovies(page).collect {
                movies.value = it
            }
        }
    }

    fun topRated(page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {

            moviesRepository.getTopRatedMovies(page).collect {
                movies.value = it
            }
        }
    }

    fun upcoming(page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            moviesRepository.getUpcomingMovies(page).collect {
                movies.value = it
            }
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




