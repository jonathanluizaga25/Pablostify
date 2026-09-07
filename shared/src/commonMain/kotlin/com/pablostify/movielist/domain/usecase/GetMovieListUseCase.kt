package com.pablostify.movielist.domain.usecase

import com.pablostify.movielist.domain.model.Movie
import com.pablostify.movielist.domain.repository.MovieRepository
import com.pablostify.movielist.domain.valueobject.MovieFilter

class GetMovieListUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(filter: MovieFilter): Result<List<Movie>> {
        return repository.getPopularMovies(filter)
    }
}
