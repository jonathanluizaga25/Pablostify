package edu.ucb.pablostify.shared.login.domain.usecase

import edu.ucb.pablostify.shared.core.domain.model.User
import edu.ucb.pablostify.shared.login.domain.model.Credentials
import edu.ucb.pablostify.shared.login.domain.repository.LoginRepository

class LoginUseCase(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(credentials: Credentials): Result<User> {
        return repository.login(credentials)
    }
}
