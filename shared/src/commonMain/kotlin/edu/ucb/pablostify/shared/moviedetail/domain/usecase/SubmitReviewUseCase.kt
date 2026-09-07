package edu.ucb.pablostify.shared.moviedetail.domain.usecase

import edu.ucb.pablostify.shared.moviedetail.domain.repository.MovieDetailRepository

class SubmitReviewUseCase(
    private val repository: MovieDetailRepository
) {
    suspend operator fun invoke(movieId: String, review: String, rating: Float): Result<Unit> {
        return repository.submitReview(movieId, review, rating)
    }
}
