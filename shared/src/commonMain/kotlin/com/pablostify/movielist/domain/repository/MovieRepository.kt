package com.pablostify.movielist.domain.repository

import com.pablostify.movielist.domain.model.Movie
import com.pablostify.movielist.domain.valueobject.MovieFilter

interface MovieRepository {
    suspend fun getPopularMovies(filter: MovieFilter): Result<List<Movie>>
}
