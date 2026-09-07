package com.pablostify.moviedetail.presentation.state

sealed interface MovieDetailEvent {
    data class VerPelicula(val movieId: String) : MovieDetailEvent
    data object WriteReviewClicked : MovieDetailEvent
    data class SubmitReview(val text: String, val rating: Float) : MovieDetailEvent
    data object BackClicked : MovieDetailEvent
}
