package edu.ucb.pablostify.shared.movielist.domain.model

data class Movie(
    val id: String,
    val title: String,
    val genres: List<String>,
    val posterUrl: String,
    val rating: Float
)
