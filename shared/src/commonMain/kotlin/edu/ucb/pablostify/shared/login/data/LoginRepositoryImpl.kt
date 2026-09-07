package edu.ucb.pablostify.shared.login.data

import edu.ucb.pablostify.shared.core.domain.model.User
import edu.ucb.pablostify.shared.login.domain.model.Credentials
import edu.ucb.pablostify.shared.login.domain.repository.LoginRepository

class LoginRepositoryImpl(
    // TODO: inyectar cliente HTTP (Ktor) y/o fuente local
) : LoginRepository {
    override suspend fun login(credentials: Credentials): Result<User> {
        // MOCK: acepta cualquier usuario/contraseña y devuelve un usuario fijo.
        // Reemplazar por la llamada real al backend cuando esté listo.
        return Result.success(
            User(
                id = "1",
                fullName = "Ana García",
                email = "anagarcia@gmail.com",
                avatarUrl = null
            )
        )
    }
}
