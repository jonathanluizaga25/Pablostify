package edu.ucb.pablostify.profile.presentation.state

import edu.ucb.pablostify.profile.domain.usecase.GetProfileUseCase
import edu.ucb.pablostify.profile.domain.usecase.LogoutUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getProfileUseCase: GetProfileUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    private val _effect = Channel<ProfileEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        onIntent(ProfileIntent.LoadProfile)
    }

    fun onIntent(intent: ProfileIntent) {
        when (intent) {
            ProfileIntent.LoadProfile -> loadProfile()
            ProfileIntent.AccountSettingsClicked -> sendEffect(ProfileEffect.NavigateToAccountSettings)
            ProfileIntent.SecurityClicked -> sendEffect(ProfileEffect.NavigateToSecurity)
            ProfileIntent.CerrarSesion -> logout()
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {
            reduce { it.copy(isLoading = true, errorMessage = null) }
            getProfileUseCase()
                .onSuccess { profile -> reduce { it.copy(isLoading = false, profile = profile) } }
                .onFailure { error ->
                    reduce { it.copy(isLoading = false, errorMessage = error.message) }
                    sendEffect(ProfileEffect.ShowError(error.message ?: "Error al cargar el perfil"))
                }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            logoutUseCase()
                .onSuccess { sendEffect(ProfileEffect.NavigateToLogin) }
                .onFailure { error ->
                    sendEffect(ProfileEffect.ShowError(error.message ?: "Error al cerrar sesión"))
                }
        }
    }

    private fun reduce(transform: (ProfileState) -> ProfileState) {
        _state.value = transform(_state.value)
    }

    private fun sendEffect(effect: ProfileEffect) {
        viewModelScope.launch { _effect.send(effect) }
    }
}
