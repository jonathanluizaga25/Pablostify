package com.pablostify.moviedetail.data

import com.pablostify.moviedetail.domain.model.MovieDetail
import com.pablostify.moviedetail.domain.repository.MovieDetailRepository

// Implementación respaldada por base de datos local (Model/DB del diagrama)
class MovieDetailRepositoryImpl(
    // TODO: inyectar cliente HTTP + base de datos local (SQLDelight, etc.)
) : MovieDetailRepository {
    override suspend fun getMovieDetail(movieId: String): Result<MovieDetail> {
        // TODO: leer de DB local o pedir al backend
        return Result.failure(NotImplementedError("Pendiente de implementar"))
    }

    override suspend fun submitReview(movieId: String, review: String, rating: Float): Result<Unit> {
        // TODO: enviar reseña al backend / guardar local
        return Result.failure(NotImplementedError("Pendiente de implementar"))
    }
}
