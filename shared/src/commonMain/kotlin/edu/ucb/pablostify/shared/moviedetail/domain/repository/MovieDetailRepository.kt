package edu.ucb.pablostify.shared.moviedetail.domain.repository

import edu.ucb.pablostify.shared.moviedetail.domain.model.MovieDetail

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: String): Result<MovieDetail>
    suspend fun submitReview(movieId: String, review: String, rating: Float): Result<Unit>
}
