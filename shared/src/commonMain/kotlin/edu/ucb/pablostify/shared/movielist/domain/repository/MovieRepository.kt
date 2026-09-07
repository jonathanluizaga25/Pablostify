package edu.ucb.pablostify.shared.movielist.domain.repository

import edu.ucb.pablostify.shared.movielist.domain.model.Movie
import edu.ucb.pablostify.shared.movielist.domain.valueobject.MovieFilter

interface MovieRepository {
    suspend fun getPopularMovies(filter: MovieFilter): Result<List<Movie>>
}
