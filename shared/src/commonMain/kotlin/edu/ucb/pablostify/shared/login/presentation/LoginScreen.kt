package edu.ucb.pablostify.shared.login.presentation

import androidx.compose.foundation.layout.Arrangement
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
import edu.ucb.pablostify.shared.login.presentation.state.LoginEffect
import edu.ucb.pablostify.shared.login.presentation.state.LoginIntent
import edu.ucb.pablostify.shared.login.presentation.state.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onNavigateToHome: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToForgotPassword: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                LoginEffect.NavigateToHome -> onNavigateToHome()
                LoginEffect.NavigateToRegister -> onNavigateToRegister()
                LoginEffect.NavigateToForgotPassword -> onNavigateToForgotPassword()
                is LoginEffect.ShowError -> { /* TODO: mostrar snackbar con effect.message */ }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("LOG IN")
        OutlinedTextField(
            value = state.username,
            onValueChange = { viewModel.onIntent(LoginIntent.UsernameChanged(it)) },
            label = { Text("Nombre de usuario") }
        )
        OutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.onIntent(LoginIntent.PasswordChanged(it)) },
            label = { Text("Contraseña") }
        )
        if (state.errorMessage != null) {
            Text(state.errorMessage!!)
        }
        Button(onClick = { viewModel.onIntent(LoginIntent.Submit) }) {
            Text(if (state.isLoading) "Entrando..." else "ENTRAR")
        }
        Text(
            "¿Olvidé mi contraseña?",
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
