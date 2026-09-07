package edu.ucb.pablostify.movielist.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.Modifier
import edu.ucb.pablostify.movielist.presentation.state.MovieListEffect
import edu.ucb.pablostify.movielist.presentation.state.MovieListIntent
import edu.ucb.pablostify.movielist.presentation.state.MovieListViewModel

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel,
    onNavigateToDetail: (String) -> Unit,
    onNavigateToAddMovie: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is MovieListEffect.NavigateToDetail -> onNavigateToDetail(effect.movieId)
                MovieListEffect.NavigateToAddMovie -> onNavigateToAddMovie()
                is MovieListEffect.ShowError -> { /* TODO: snackbar */ }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text("PELÍCULAS POPULARES")
        OutlinedTextField(
            value = state.query,
            onValueChange = { viewModel.onIntent(MovieListIntent.Filtrar(it)) },
            placeholder = { Text("Buscar películas...") }
        )
        LazyColumn {
            items(state.movies) { movie ->
                Text(
                    movie.title,
                    modifier = Modifier.Companion // placeholder click via onClick modifier real
                )
            }
        }
    }
}
