package com.pablostify.movielist.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.pablostify.movielist.presentation.state.MovieListEffect
import com.pablostify.movielist.presentation.state.MovieListIntent
import com.pablostify.movielist.presentation.state.MovieListViewModel

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel,
    onNavigateToDetail: (String) -> Unit,
    onNavigateToAddMovie: () -> Unit
) {
    val state by viewModel.state.collectAsState()

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
