package com.pablostify.login.presentation.state

sealed interface LoginEffect {
    data object NavigateToHome : LoginEffect
    data object NavigateToRegister : LoginEffect
    data object NavigateToForgotPassword : LoginEffect
    data class ShowError(val message: String) : LoginEffect
}
