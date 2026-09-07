package edu.ucb.pablostify.shared.login.presentation.state

import edu.ucb.pablostify.shared.login.domain.model.Credentials
import edu.ucb.pablostify.shared.login.domain.usecase.LoginUseCase
import edu.ucb.pablostify.shared.login.domain.valueobject.Password
import edu.ucb.pablostify.shared.login.domain.valueobject.Username
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

// Reducer / ViewModel MVI: recibe Intent, produce nuevo State + Effect
class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private val _effect = Channel<LoginEffect>()
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.UsernameChanged -> reduce { it.copy(username = intent.value, errorMessage = null) }
            is LoginIntent.PasswordChanged -> reduce { it.copy(password = intent.value, errorMessage = null) }
            LoginIntent.TogglePasswordVisibility -> reduce { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            LoginIntent.Submit -> submit()
            LoginIntent.ForgotPasswordClicked -> sendEffect(LoginEffect.NavigateToForgotPassword)
            LoginIntent.GoToRegisterClicked -> sendEffect(LoginEffect.NavigateToRegister)
        }
    }

    private fun submit() {
        val current = _state.value
        viewModelScope.launch {
            reduce { it.copy(isLoading = true, errorMessage = null) }
            val credentials = try {
                Credentials(Username(current.username), Password(current.password))
            } catch (e: IllegalArgumentException) {
                reduce { it.copy(isLoading = false, errorMessage = e.message) }
                return@launch
            }
            loginUseCase(credentials)
                .onSuccess {
                    reduce { it.copy(isLoading = false) }
                    sendEffect(LoginEffect.NavigateToHome)
                }
                .onFailure { error ->
                    reduce { it.copy(isLoading = false, errorMessage = error.message) }
                    sendEffect(LoginEffect.ShowError(error.message ?: "Error al iniciar sesión"))
                }
        }
    }

    private fun reduce(transform: (LoginState) -> LoginState) {
        _state.value = transform(_state.value)
    }

    private fun sendEffect(effect: LoginEffect) {
        viewModelScope.launch { _effect.send(effect) }
    }
}
