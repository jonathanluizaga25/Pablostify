package com.pablostify.moviedetail.presentation.state

import com.pablostify.moviedetail.domain.model.MovieDetail

data class MovieDetailState(
    val movie: MovieDetail? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
