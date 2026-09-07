package edu.ucb.pablostify.shared.login.domain.model

import edu.ucb.pablostify.shared.login.domain.valueobject.Password
import edu.ucb.pablostify.shared.login.domain.valueobject.Username

data class Credentials(
    val username: Username,
    val password: Password
)
