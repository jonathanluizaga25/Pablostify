package edu.ucb.pablostify.shared.movielist.presentation.state

import edu.ucb.pablostify.shared.movielist.domain.usecase.GetMovieListUseCase
import edu.ucb.pablostify.shared.movielist.domain.valueobject.MovieFilter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val getMovieListUseCase: GetMovieListUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MovieListState())
    val state: StateFlow<MovieListState> = _state.asStateFlow()

    private val _effect = Channel<MovieListEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        onIntent(MovieListIntent.LoadMovies)
    }

    fun onIntent(intent: MovieListIntent) {
        when (intent) {
            is MovieListIntent.Filtrar -> {
                reduce { it.copy(query = intent.query) }
                loadMovies(intent.query)
            }
            MovieListIntent.LoadMovies -> loadMovies(_state.value.query)
            is MovieListIntent.MovieClicked -> sendEffect(MovieListEffect.NavigateToDetail(intent.movieId))
            MovieListIntent.AddMovieClicked -> sendEffect(MovieListEffect.NavigateToAddMovie)
        }
    }

    private fun loadMovies(query: String) {
        viewModelScope.launch {
            reduce { it.copy(isLoading = true, errorMessage = null) }
            getMovieListUseCase(MovieFilter(query))
                .onSuccess { movies -> reduce { it.copy(isLoading = false, movies = movies) } }
                .onFailure { error ->
                    reduce { it.copy(isLoading = false, errorMessage = error.message) }
                    sendEffect(MovieListEffect.ShowError(error.message ?: "Error al cargar películas"))
                }
        }
    }

    private fun reduce(transform: (MovieListState) -> MovieListState) {
        _state.value = transform(_state.value)
    }

    private fun sendEffect(effect: MovieListEffect) {
        viewModelScope.launch { _effect.send(effect) }
    }
}
