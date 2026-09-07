package edu.ucb.pablostify.shared.movielist.data

import edu.ucb.pablostify.shared.movielist.domain.model.Movie
import edu.ucb.pablostify.shared.movielist.domain.repository.MovieRepository
import edu.ucb.pablostify.shared.movielist.domain.valueobject.MovieFilter

// Implementación con cache en memoria/local (Model/Cache del diagrama)
class MovieRepositoryImpl(
    // TODO: inyectar cliente HTTP + fuente de cache local
) : MovieRepository {

    private val allMovies = listOf(
        Movie(
            id = "matrix",
            title = "The Matrix",
            genres = listOf("Acción", "Ciencia ficción"),
            posterUrl = "",
            rating = 4.8f
        ),
        Movie(
            id = "spiderman",
            title = "Spider-Man",
            genres = listOf("Acción", "Aventura"),
            posterUrl = "",
            rating = 4.5f
        ),
        Movie(
            id = "interstellar",
            title = "Interstellar",
            genres = listOf("Ciencia ficción", "Drama"),
            posterUrl = "",
            rating = 4.9f
        )
    )

    override suspend fun getPopularMovies(filter: MovieFilter): Result<List<Movie>> {
        // MOCK: filtra la lista fija por título si hay texto de búsqueda.
        val movies = if (filter.query.isBlank()) {
            allMovies
        } else {
            allMovies.filter { it.title.contains(filter.query, ignoreCase = true) }
        }
        return Result.success(movies)
    }
}
