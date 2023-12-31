package com.example.moviemaster.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.ImageLoader
import com.example.moviemaster.ui.screens.FavoriteMoviesScreen
import com.example.moviemaster.ui.screens.HomeScreen


@Composable
fun NavigationHost(
    navController: NavHostController,
    snackBarHostState: SnackbarHostState,
    imageLoader: ImageLoader
) {
    NavHost(navController = navController, startDestination = Rout.Home.screen_route) {
        composable(Rout.Home.screen_route) {
            HomeScreen(navController = navController, snackBarHostState = snackBarHostState,imageLoader=imageLoader)
        }

        composable(Rout.Favorites.screen_route) {
            FavoriteMoviesScreen(navController, hiltViewModel(),imageLoader=imageLoader)
        }

    }
}