package com.pablostify.movielist.presentation.state

import com.pablostify.movielist.domain.model.Movie

data class MovieListState(
    val query: String = "",
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
