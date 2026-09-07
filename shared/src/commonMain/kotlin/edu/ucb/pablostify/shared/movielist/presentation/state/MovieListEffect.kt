package edu.ucb.pablostify.shared.movielist.presentation.state

sealed interface MovieListEffect {
    data class NavigateToDetail(val movieId: String) : MovieListEffect
    data object NavigateToAddMovie : MovieListEffect
    data class ShowError(val message: String) : MovieListEffect
}
