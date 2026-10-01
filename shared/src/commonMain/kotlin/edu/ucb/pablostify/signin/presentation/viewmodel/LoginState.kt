package edu.ucb.pablostify.signin.presentation.viewmodel

data class LoginState(
    val email: String = "",
    val password: String = "",
    val error: String? = null,
    val isLoading : Boolean = false
)