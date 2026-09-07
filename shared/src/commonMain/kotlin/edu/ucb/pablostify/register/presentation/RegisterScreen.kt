package edu.ucb.pablostify.register.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.ucb.pablostify.register.presentation.state.RegisterEffect
import edu.ucb.pablostify.register.presentation.state.RegisterEvent
import edu.ucb.pablostify.register.presentation.state.RegisterViewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    onNavigateToHome: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                RegisterEffect.NavigateToHome -> onNavigateToHome()
                RegisterEffect.NavigateToLogin -> onNavigateToLogin()
                is RegisterEffect.ShowError -> { /* TODO: snackbar */ }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("REGISTRO")
        OutlinedTextField(
            value = state.fullName,
            onValueChange = { viewModel.onEvent(RegisterEvent.FullNameChanged(it)) },
            label = { Text("Nombre Completo") }
        )
        OutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.onEvent(RegisterEvent.EmailChanged(it)) },
            label = { Text("Email") }
        )
        OutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.onEvent(RegisterEvent.PasswordChanged(it)) },
            label = { Text("Contraseña") }
        )
        OutlinedTextField(
            value = state.confirmPassword,
            onValueChange = { viewModel.onEvent(RegisterEvent.ConfirmPasswordChanged(it)) },
            label = { Text("Confirmar Contraseña") }
        )
        if (state.errorMessage != null) {
            Text(state.errorMessage!!)
        }
        Button(onClick = { viewModel.onEvent(RegisterEvent.Submit) }) {
            Text(if (state.isLoading) "Creando..." else "CREAR CUENTA")
        }
        Text("Ya tengo cuenta", modifier = Modifier.padding(top = 8.dp))
    }
}
