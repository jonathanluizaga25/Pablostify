package com.pablostify.login.domain.model

import com.pablostify.login.domain.valueobject.Password
import com.pablostify.login.domain.valueobject.Username

data class Credentials(
    val username: Username,
    val password: Password
)
