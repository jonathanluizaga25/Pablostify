package com.pablostify.moviedetail.domain.usecase

import com.pablostify.moviedetail.domain.repository.MovieDetailRepository

class SubmitReviewUseCase(
    private val repository: MovieDetailRepository
) {
    suspend operator fun invoke(movieId: String, review: String, rating: Float): Result<Unit> {
        return repository.submitReview(movieId, review, rating)
    }
}
