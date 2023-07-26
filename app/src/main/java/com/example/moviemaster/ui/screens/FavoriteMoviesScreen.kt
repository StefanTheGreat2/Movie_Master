package com.example.moviemaster.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
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
import com.example.moviemaster.ui.ImageLoader.MovieImage
import com.example.moviemaster.ui.theme.transparentBlack


@Composable
fun FavoriteMoviesScreen(navController: NavController,mainViewModel: MainViewModel) {
    val favoriteMovies = mainViewModel.getFavoriteMovies().collectAsState(initial = emptyList())

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        favoriteMovies.value.forEach { movie ->
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
               Column{
                MovieImage(posterPath = movie.posterPath)
                Text(text = movie.title, fontSize = 24.sp)
                Text(text = movie.overview)
               }
            }
        }
    }
    Box(
        modifier = Modifier
            .padding(top = 20.dp, start = 10.dp)
            .clip(CircleShape)
            .background(transparentBlack.copy(0.7f)).size(45.dp), contentAlignment = Alignment.Center

    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier.clickable { navController.popBackStack() }.size(35.dp),
            tint = Color.LightGray
        )

    }
}