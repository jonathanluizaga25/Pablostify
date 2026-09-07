package edu.ucb.pablostify.register.presentation.state

sealed interface RegisterEvent {
    data class FullNameChanged(val value: String) : RegisterEvent
    data class EmailChanged(val value: String) : RegisterEvent
    data class PasswordChanged(val value: String) : RegisterEvent
    data class ConfirmPasswordChanged(val value: String) : RegisterEvent
    data object Submit : RegisterEvent
    data object GoToLoginClicked : RegisterEvent
}
