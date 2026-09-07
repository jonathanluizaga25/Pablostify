package edu.ucb.pablostify.shared.profile.domain.model

import edu.ucb.pablostify.shared.core.domain.model.User
import edu.ucb.pablostify.shared.profile.domain.valueobject.SessionStatus

data class UserProfile(
    val user: User?,
    val favoriteMovieIds: List<String>,
    val sessionStatus: SessionStatus
)
