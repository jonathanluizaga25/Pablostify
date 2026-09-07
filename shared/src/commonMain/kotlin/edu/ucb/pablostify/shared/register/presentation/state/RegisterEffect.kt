package edu.ucb.pablostify.shared.register.presentation.state

sealed interface RegisterEffect {
    data object NavigateToHome : RegisterEffect
    data object NavigateToLogin : RegisterEffect
    data class ShowError(val message: String) : RegisterEffect
}
