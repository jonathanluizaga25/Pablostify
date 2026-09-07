package edu.ucb.pablostify.movielist.presentation.state

sealed interface MovieListIntent {
    data class Filtrar(val query: String) : MovieListIntent
    data object LoadMovies : MovieListIntent
    data class MovieClicked(val movieId: String) : MovieListIntent
    data object AddMovieClicked : MovieListIntent
}
