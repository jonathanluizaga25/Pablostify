package com.pablostify.movielist.data

import com.pablostify.movielist.domain.model.Movie
import com.pablostify.movielist.domain.repository.MovieRepository
import com.pablostify.movielist.domain.valueobject.MovieFilter

// Implementación con cache en memoria/local (Model/Cache del diagrama)
class MovieRepositoryImpl(
    // TODO: inyectar cliente HTTP + fuente de cache local
) : MovieRepository {
    private var cache: List<Movie> = emptyList()

    override suspend fun getPopularMovies(filter: MovieFilter): Result<List<Movie>> {
        // TODO: si hay cache válida y filter.query vacío, devolver cache;
        // si no, pedir al backend y actualizar cache
        return Result.failure(NotImplementedError("Pendiente de implementar"))
    }
}
