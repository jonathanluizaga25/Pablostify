package edu.ucb.pablostify.shared.moviedetail.domain.usecase

import edu.ucb.pablostify.shared.moviedetail.domain.model.MovieDetail
import edu.ucb.pablostify.shared.moviedetail.domain.repository.MovieDetailRepository

class GetMovieDetailUseCase(
    private val repository: MovieDetailRepository
) {
    suspend operator fun invoke(movieId: String): Result<MovieDetail> {
        return repository.getMovieDetail(movieId)
    }
}
