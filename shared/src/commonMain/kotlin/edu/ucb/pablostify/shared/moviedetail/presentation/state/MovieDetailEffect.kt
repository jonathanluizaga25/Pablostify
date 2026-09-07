package edu.ucb.pablostify.shared.moviedetail.presentation.state

sealed interface MovieDetailEffect {
    data object NavigateBack : MovieDetailEffect
    data object ShowReviewDialog : MovieDetailEffect
    data class ShowError(val message: String) : MovieDetailEffect
}
