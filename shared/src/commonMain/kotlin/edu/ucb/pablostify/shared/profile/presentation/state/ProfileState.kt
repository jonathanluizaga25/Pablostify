package edu.ucb.pablostify.shared.profile.presentation.state

import edu.ucb.pablostify.shared.profile.domain.model.UserProfile

data class ProfileState(
    val profile: UserProfile? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
