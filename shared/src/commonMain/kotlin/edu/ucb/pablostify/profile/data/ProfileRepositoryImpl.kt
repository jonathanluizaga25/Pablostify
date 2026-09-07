package edu.ucb.pablostify.profile.data

import edu.ucb.pablostify.core.domain.model.User
import edu.ucb.pablostify.profile.domain.model.UserProfile
import edu.ucb.pablostify.profile.domain.repository.ProfileRepository
import edu.ucb.pablostify.profile.domain.valueobject.SessionStatus

// Implementación respaldada por red/autenticación (network/auth del diagrama)
class ProfileRepositoryImpl(
    // TODO: inyectar cliente HTTP + almacenamiento de sesión (auth)
) : ProfileRepository {
    override suspend fun getProfile(): Result<UserProfile> {
        // MOCK: perfil fijo, "logueado", con 3 favoritas.
        return Result.success(
            UserProfile(
                user = User(
                    id = "1",
                    fullName = "Ana García",
                    email = "anagarcia@gmail.com",
                    avatarUrl = null
                ),
                favoriteMovieIds = listOf("matrix", "spiderman", "interstellar"),
                sessionStatus = SessionStatus.LOGGED_IN
            )
        )
    }

    override suspend fun logout(): Result<Unit> {
        // MOCK: simula éxito, no borra nada real todavía.
        return Result.success(Unit)
    }
}
