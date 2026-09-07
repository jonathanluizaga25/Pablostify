package edu.ucb.pablostify.shared.moviedetail.presentation.state

import edu.ucb.pablostify.shared.moviedetail.domain.model.MovieDetail

data class MovieDetailState(
    val movie: MovieDetail? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
