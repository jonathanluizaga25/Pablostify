package com.pablostify.moviedetail.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.pablostify.moviedetail.presentation.state.MovieDetailEffect
import com.pablostify.moviedetail.presentation.state.MovieDetailEvent
import com.pablostify.moviedetail.presentation.state.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    movieId: String,
    viewModel: MovieDetailViewModel,
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.onEvent(MovieDetailEvent.VerPelicula(movieId))
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                MovieDetailEffect.NavigateBack -> onNavigateBack()
                MovieDetailEffect.ShowReviewDialog -> { /* TODO: mostrar diálogo de reseña */ }
                is MovieDetailEffect.ShowError -> { /* TODO: snackbar */ }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        val movie = state.movie
        if (movie != null) {
            Text(movie.title)
            Text(movie.synopsis)
            Button(onClick = { viewModel.onEvent(MovieDetailEvent.WriteReviewClicked) }) {
                Text("ESCRIBIR RESEÑA")
            }
        } else if (state.isLoading) {
            Text("Cargando...")
        }
    }
}
