package com.pablostify.login.presentation.state

import com.pablostify.login.domain.model.Credentials
import com.pablostify.login.domain.usecase.LoginUseCase
import com.pablostify.login.domain.valueobject.Password
import com.pablostify.login.domain.valueobject.Username
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

// Reducer / ViewModel MVI: recibe Intent, produce nuevo State + Effect
class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob())
) {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private val _effect = Channel<LoginEffect>()
    val effect = _effect.receiveAsFlow()


    private fun submit() {
        val current = _state.value
        scope.launch {
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
        scope.launch { _effect.send(effect) }
    }
}
