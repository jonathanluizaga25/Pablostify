package edu.ucb.pablostify.signin.presentation.viewmodel

sealed interface LoginEffects {
    object NavigateToHome: LoginEffects
    object SignUp: LoginEffects
    data class ShowToast(val message: String): LoginEffects
}