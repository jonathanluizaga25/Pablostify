package edu.ucb.pablostify.navigation

sealed interface Route {
    data object Login : Route
    data object Register : Route
    data object MovieList : Route
    data class MovieDetail(val movieId: String) : Route
    data object Profile : Route
}
