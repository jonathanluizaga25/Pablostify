package com.pablostify.moviedetail.domain.repository

import com.pablostify.moviedetail.domain.model.MovieDetail

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: String): Result<MovieDetail>
    suspend fun submitReview(movieId: String, review: String, rating: Float): Result<Unit>
}
