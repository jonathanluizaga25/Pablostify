package edu.ucb.pablostify.shared.movielist.domain.usecase

import edu.ucb.pablostify.shared.movielist.domain.model.Movie
import edu.ucb.pablostify.shared.movielist.domain.repository.MovieRepository
import edu.ucb.pablostify.shared.movielist.domain.valueobject.MovieFilter

class GetMovieListUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(filter: MovieFilter): Result<List<Movie>> {
        return repository.getPopularMovies(filter)
    }
}
