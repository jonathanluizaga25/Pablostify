package edu.ucb.pablostify.moviedetail.data

import edu.ucb.pablostify.moviedetail.domain.model.CastMember
import edu.ucb.pablostify.moviedetail.domain.model.MovieDetail
import edu.ucb.pablostify.moviedetail.domain.repository.MovieDetailRepository
import edu.ucb.pablostify.moviedetail.domain.valueobject.Rating

// Implementación respaldada por base de datos local (Model/DB del diagrama)
class MovieDetailRepositoryImpl(
    // TODO: inyectar cliente HTTP + base de datos local (SQLDelight, etc.)
) : MovieDetailRepository {

    private val details = mapOf(
        "matrix" to MovieDetail(
            id = "matrix",
            title = "The Matrix",
            synopsis = "Un programador descubre que la realidad que conoce es una simulación controlada por máquinas.",
            posterUrl = "",
            cast = listOf(
                CastMember("1", "Keanu Reeves", ""),
                CastMember("2", "Laurence Fishburne", ""),
                CastMember("3", "Carrie-Anne Moss", "")
            ),
            rating = Rating(8.7f)
        ),
        "spiderman" to MovieDetail(
            id = "spiderman",
            title = "Spider-Man",
            synopsis = "Un joven adquiere poderes arácnidos y debe aprender a usarlos para proteger su ciudad.",
            posterUrl = "",
            cast = listOf(
                CastMember("4", "Tom Holland", ""),
                CastMember("5", "Zendaya", "")
            ),
            rating = Rating(7.9f)
        ),
        "interstellar" to MovieDetail(
            id = "interstellar",
            title = "Interstellar",
            synopsis = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incidunt ut labore.",
            posterUrl = "",
            cast = listOf(
                CastMember("6", "Matthew McConaughey", ""),
                CastMember("7", "Anne Hathaway", ""),
                CastMember("8", "Jessica Chastain", "")
            ),
            rating = Rating(8.0f)
        )
    )

    override suspend fun getMovieDetail(movieId: String): Result<MovieDetail> {
        // MOCK: busca en el mapa fijo de arriba.
        val detail = details[movieId]
        return if (detail != null) {
            Result.success(detail)
        } else {
            Result.failure(NoSuchElementException("Película no encontrada: $movieId"))
        }
    }

    override suspend fun submitReview(movieId: String, review: String, rating: Float): Result<Unit> {
        // MOCK: no guarda nada todavía, solo simula éxito.
        return Result.success(Unit)
    }
}
