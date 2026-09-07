package edu.ucb.pablostify.shared.profile.domain.repository

import edu.ucb.pablostify.shared.profile.domain.model.UserProfile

interface ProfileRepository {
    suspend fun getProfile(): Result<UserProfile>
    suspend fun logout(): Result<Unit>
}
