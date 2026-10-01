package edu.ucb.pablostify.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import edu.ucb.pablostify.signin.presentation.viewmodel.LoginViewModel

val presentationModule = module {
    viewModelOf(::LoginViewModel)
}