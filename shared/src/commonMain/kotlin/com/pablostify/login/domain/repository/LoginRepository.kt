package com.pablostify.login.domain.repository

import com.pablostify.core.domain.model.User
import com.pablostify.login.domain.model.Credentials

interface LoginRepository {
    suspend fun login(credentials: Credentials): Result<User>
}
