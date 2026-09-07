package edu.ucb.pablostify.shared.movielist.presentation.state

import edu.ucb.pablostify.shared.movielist.domain.model.Movie

data class MovieListState(
    val query: String = "",
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
