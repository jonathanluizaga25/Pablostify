package edu.ucb.pablostify.login.presentation.state

sealed interface LoginIntent {
    data class UsernameChanged(val value: String) : LoginIntent
    data class PasswordChanged(val value: String) : LoginIntent
    data object TogglePasswordVisibility : LoginIntent
    data object Submit : LoginIntent
    data object ForgotPasswordClicked : LoginIntent
    data object GoToRegisterClicked : LoginIntent
}
