package edu.ucb.pablostify


import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import edu.ucb.pablostify.signin.presentation.screen.LoginScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
    LoginScreen()
    }
}