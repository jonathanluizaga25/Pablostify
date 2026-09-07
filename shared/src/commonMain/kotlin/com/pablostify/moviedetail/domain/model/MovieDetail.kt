package com.pablostify.moviedetail.domain.model

import com.pablostify.moviedetail.domain.valueobject.Rating

data class MovieDetail(
    val id: String,
    val title: String,
    val synopsis: String,
    val posterUrl: String,
    val cast: List<CastMember>,
    val rating: Rating
)
