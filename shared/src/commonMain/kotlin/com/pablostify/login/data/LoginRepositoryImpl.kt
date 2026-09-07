package com.pablostify.login.data

import com.pablostify.core.domain.model.User
import com.pablostify.login.domain.model.Credentials
import com.pablostify.login.domain.repository.LoginRepository

class LoginRepositoryImpl(
) : LoginRepository {
    override suspend fun login(credentials: Credentials): Result<User> {
        return Result.failure(NotImplementedError("Pendiente de implementar"))
    }
}
