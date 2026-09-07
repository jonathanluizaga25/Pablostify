package edu.ucb.pablostify.profile.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.Modifier
import edu.ucb.pablostify.profile.presentation.state.ProfileEffect
import edu.ucb.pablostify.profile.presentation.state.ProfileIntent
import edu.ucb.pablostify.profile.presentation.state.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onNavigateToLogin: () -> Unit,
    onNavigateToAccountSettings: () -> Unit,
    onNavigateToSecurity: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                ProfileEffect.NavigateToLogin -> onNavigateToLogin()
                ProfileEffect.NavigateToAccountSettings -> onNavigateToAccountSettings()
                ProfileEffect.NavigateToSecurity -> onNavigateToSecurity()
                is ProfileEffect.ShowError -> { /* TODO: snackbar */ }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text("MI PERFIL")
        state.profile?.user?.let { user ->
            Text(user.fullName)
            Text(user.email)
        }
        Button(onClick = { viewModel.onIntent(ProfileIntent.AccountSettingsClicked) }) {
            Text("Ajustes de Cuenta")
        }
        Button(onClick = { viewModel.onIntent(ProfileIntent.SecurityClicked) }) {
            Text("Seguridad")
        }
        Button(onClick = { viewModel.onIntent(ProfileIntent.CerrarSesion) }) {
            Text("CERRAR SESIÓN")
        }
    }
}
