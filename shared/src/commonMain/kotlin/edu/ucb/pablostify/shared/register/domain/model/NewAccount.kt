package edu.ucb.pablostify.shared.register.domain.model

import edu.ucb.pablostify.shared.register.domain.valueobject.Email
import edu.ucb.pablostify.shared.register.domain.valueobject.Password

data class NewAccount(
    val fullName: String,
    val email: Email,
    val password: Password
)
