package edu.ucb.pablostify.shared.register.domain.repository

import edu.ucb.pablostify.shared.core.domain.model.User
import edu.ucb.pablostify.shared.register.domain.model.NewAccount

interface RegisterRepository {
    suspend fun register(account: NewAccount): Result<User>
}
