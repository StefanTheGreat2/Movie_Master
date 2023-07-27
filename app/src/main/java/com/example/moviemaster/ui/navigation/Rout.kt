package com.example.moviemaster.ui.navigation

sealed class Rout( var screen_route: String) {

    object Home : Rout( "movie_home")

    object Favorites : Rout( "favorite_movies")
}