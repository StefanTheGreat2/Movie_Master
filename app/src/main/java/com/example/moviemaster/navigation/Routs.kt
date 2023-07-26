package com.example.moviemaster.navigation

sealed class Routs(var title: String, var screen_route: String) {

    object Home : Routs("MovieHome", "movie_home")

    object Favorites : Routs("FavoriteMovies", "favorite_movies")
}