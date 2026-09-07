package edu.ucb.pablostify.shared.profile.domain.usecase

import edu.ucb.pablostify.shared.profile.domain.model.UserProfile
import edu.ucb.pablostify.shared.profile.domain.repository.ProfileRepository

class GetProfileUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(): Result<UserProfile> {
        return repository.getProfile()
    }
}
