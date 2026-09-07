package edu.ucb.pablostify.shared.login.domain.repository

import edu.ucb.pablostify.shared.core.domain.model.User
import edu.ucb.pablostify.shared.login.domain.model.Credentials

interface LoginRepository {
    suspend fun login(credentials: Credentials): Result<User>
}
