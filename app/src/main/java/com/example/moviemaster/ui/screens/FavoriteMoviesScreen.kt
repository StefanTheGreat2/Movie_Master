package com.example.moviemaster.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moviemaster.ui.imageLoader.MovieImage
import com.example.moviemaster.ui.buttons.PopBackButton
import com.example.moviemaster.ui.theme.transparentBlack


@Composable
fun FavoriteMoviesScreen(navController: NavController, mainViewModel: MainViewModel) {
    val favoriteMovies = mainViewModel.getFavoriteMovies().collectAsState(initial = emptyList())

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        favoriteMovies.value.forEach { movie ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(CircleShape.copy(CornerSize(15.dp)))
                    .padding(8.dp)
                    .background(
                        transparentBlack.copy(0.5f),
                        shape = CircleShape.copy(CornerSize(15.dp))
                    ), contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(4.dp)) {
                    MovieImage(posterPath = movie.posterPath)
                    Text(text = movie.title, fontSize = 24.sp, color = Color.White)
                    Text(text = movie.overview, color = Color.LightGray)
                }
            }
        }
    }
    PopBackButton { navController.popBackStack() }
}

