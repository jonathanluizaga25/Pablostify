package edu.ucb.pablostify.shared.register.presentation.state

import edu.ucb.pablostify.shared.register.domain.model.NewAccount
import edu.ucb.pablostify.shared.register.domain.usecase.RegisterUseCase
import edu.ucb.pablostify.shared.register.domain.valueobject.Email
import edu.ucb.pablostify.shared.register.domain.valueobject.Password
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state.asStateFlow()

    private val _effect = Channel<RegisterEffect>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.FullNameChanged -> reduce { it.copy(fullName = event.value) }
            is RegisterEvent.EmailChanged -> reduce { it.copy(email = event.value) }
            is RegisterEvent.PasswordChanged -> reduce { it.copy(password = event.value) }
            is RegisterEvent.ConfirmPasswordChanged -> reduce { it.copy(confirmPassword = event.value) }
            RegisterEvent.Submit -> submit()
            RegisterEvent.GoToLoginClicked -> sendEffect(RegisterEffect.NavigateToLogin)
        }
    }

    private fun submit() {
        val current = _state.value
        if (current.password != current.confirmPassword) {
            reduce { it.copy(errorMessage = "Las contraseñas no coinciden") }
            return
        }
        viewModelScope.launch {
            reduce { it.copy(isLoading = true, errorMessage = null) }
            val account = try {
                NewAccount(current.fullName, Email(current.email), Password(current.password))
            } catch (e: IllegalArgumentException) {
                reduce { it.copy(isLoading = false, errorMessage = e.message) }
                return@launch
            }
            registerUseCase(account)
                .onSuccess {
                    reduce { it.copy(isLoading = false) }
                    sendEffect(RegisterEffect.NavigateToHome)
                }
                .onFailure { error ->
                    reduce { it.copy(isLoading = false, errorMessage = error.message) }
                    sendEffect(RegisterEffect.ShowError(error.message ?: "Error al registrarse"))
                }
        }
    }

    private fun reduce(transform: (RegisterState) -> RegisterState) {
        _state.value = transform(_state.value)
    }

    private fun sendEffect(effect: RegisterEffect) {
        viewModelScope.launch { _effect.send(effect) }
    }
}
