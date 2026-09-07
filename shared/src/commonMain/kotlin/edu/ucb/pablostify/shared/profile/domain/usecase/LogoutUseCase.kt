package edu.ucb.pablostify.shared.profile.domain.usecase

import edu.ucb.pablostify.shared.profile.domain.repository.ProfileRepository

class LogoutUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return repository.logout()
    }
}
