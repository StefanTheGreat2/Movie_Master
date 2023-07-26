package com.example.moviemaster.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviemaster.ui.screens.FavoriteMoviesScreen
import com.example.moviemaster.ui.screens.HomeScreen


@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routs.Home.screen_route) {
        composable(Routs.Home.screen_route) {
            HomeScreen(navController = navController)
        }

            composable(Routs.Favorites.screen_route) {
                FavoriteMoviesScreen(navController, hiltViewModel())
            }

            }
    }