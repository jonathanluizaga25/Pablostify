package com.pablostify.moviedetail.presentation.state

import com.pablostify.moviedetail.domain.usecase.GetMovieDetailUseCase
import com.pablostify.moviedetail.domain.usecase.SubmitReviewUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val submitReviewUseCase: SubmitReviewUseCase,
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob())
) {
    private val _state = MutableStateFlow(MovieDetailState())
    val state: StateFlow<MovieDetailState> = _state.asStateFlow()

    private val _effect = Channel<MovieDetailEffect>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.VerPelicula -> loadMovie(event.movieId)
            MovieDetailEvent.WriteReviewClicked -> sendEffect(MovieDetailEffect.ShowReviewDialog)
            is MovieDetailEvent.SubmitReview -> submitReview(event.text, event.rating)
            MovieDetailEvent.BackClicked -> sendEffect(MovieDetailEffect.NavigateBack)
        }
    }

    private fun loadMovie(movieId: String) {
        scope.launch {
            reduce { it.copy(isLoading = true, errorMessage = null) }
            getMovieDetailUseCase(movieId)
                .onSuccess { movie -> reduce { it.copy(isLoading = false, movie = movie) } }
                .onFailure { error ->
                    reduce { it.copy(isLoading = false, errorMessage = error.message) }
                    sendEffect(MovieDetailEffect.ShowError(error.message ?: "Error al cargar la película"))
                }
        }
    }

    private fun submitReview(text: String, rating: Float) {
        val movieId = _state.value.movie?.id ?: return
        scope.launch {
            submitReviewUseCase(movieId, text, rating)
                .onFailure { error ->
                    sendEffect(MovieDetailEffect.ShowError(error.message ?: "Error al enviar la reseña"))
                }
        }
    }

    private fun reduce(transform: (MovieDetailState) -> MovieDetailState) {
        _state.value = transform(_state.value)
    }

    private fun sendEffect(effect: MovieDetailEffect) {
        scope.launch { _effect.send(effect) }
    }
}
