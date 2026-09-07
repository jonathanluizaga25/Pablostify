package edu.ucb.pablostify.moviedetail.presentation.state

import edu.ucb.pablostify.moviedetail.domain.model.MovieDetail

data class MovieDetailState(
    val movie: MovieDetail? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
