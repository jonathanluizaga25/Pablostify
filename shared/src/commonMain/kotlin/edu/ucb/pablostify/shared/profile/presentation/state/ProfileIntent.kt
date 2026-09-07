package edu.ucb.pablostify.shared.profile.presentation.state

sealed interface ProfileIntent {
    data object LoadProfile : ProfileIntent
    data object AccountSettingsClicked : ProfileIntent
    data object SecurityClicked : ProfileIntent
    data object CerrarSesion : ProfileIntent
}
