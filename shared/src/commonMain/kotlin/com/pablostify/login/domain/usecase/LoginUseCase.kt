package com.pablostify.login.domain.usecase

import com.pablostify.core.domain.model.User
import com.pablostify.login.domain.model.Credentials
import com.pablostify.login.domain.repository.LoginRepository

class LoginUseCase(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(credentials: Credentials): Result<User> {
        return repository.login(credentials)
    }
}
