package edu.ucb.pablostify.profile.presentation.state

sealed interface ProfileEffect {
    data object NavigateToLogin : ProfileEffect
    data object NavigateToAccountSettings : ProfileEffect
    data object NavigateToSecurity : ProfileEffect
    data class ShowError(val message: String) : ProfileEffect
}
