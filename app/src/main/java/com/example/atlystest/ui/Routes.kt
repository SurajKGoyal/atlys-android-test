package com.example.atlystest.ui

sealed class Routes(val route: String) {
    object TrendingMoviesScreen : Routes("trending_movies_screen")
    object MovieDetailsScreen : Routes("movie_details_screen")
    object ErrorScreen: Routes("error_screen")
    object LoadingScreen: Routes("loading_screen")
}