package com.example.moviemaster.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.example.moviemaster.data.models.entities.Movie
import com.example.moviemaster.data.models.response.MovieDetails
import com.example.moviemaster.ui.ImageLoader.MovieImage
import com.example.moviemaster.ui.theme.transparentBlack
import kotlinx.coroutines.launch

@Composable
fun MovieDetailsScreen(movie: MovieDetails, viewModel: MainViewModel) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .background(transparentBlack.copy(alpha = 0.8f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            MovieImage(movie.posterPath)
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Icon(
                    modifier = Modifier.clickable {
                        val currentMovie = Movie(
                            movie.backdropPath,
                            movie.genreIds,
                            movie.originalLanguage,
                            movie.originalTitle,
                            movie.overview,
                            movie.popularity,
                            movie.posterPath,
                            movie.releaseDate,
                            movie.title,
                            movie.voteAverage,
                            movie.voteCount,
                            movie.id
                        )
                        viewModel.viewModelScope.launch {
                            viewModel.getFavoriteMovieById(movie.id).collect { movie ->
                                if (movie != null) {
                                    viewModel.deleteMovieFromFavorites(currentMovie)
                                } else {
                                    viewModel.saveMovieToFavorites(currentMovie)
                                }
                            }

                        }

                    }.size(35.dp),
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorites",
                    tint = Color.Yellow
                )
            }
            Text(
                modifier = Modifier.padding(4.dp),
                text = movie.title,
                fontSize = 24.sp,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(4.dp),
                text = movie.overview,
                fontSize = 18.sp,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(6.dp),
                text = "Release Date : ${movie.releaseDate}",
                fontSize = 18.sp,
                color = Color.White
            )

        }
    }
}

