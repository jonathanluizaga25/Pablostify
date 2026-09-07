package edu.ucb.pablostify.shared.register.domain.usecase

import edu.ucb.pablostify.shared.core.domain.model.User
import edu.ucb.pablostify.shared.register.domain.model.NewAccount
import edu.ucb.pablostify.shared.register.domain.repository.RegisterRepository

class RegisterUseCase(
    private val repository: RegisterRepository
) {
    suspend operator fun invoke(account: NewAccount): Result<User> {
        return repository.register(account)
    }
}
