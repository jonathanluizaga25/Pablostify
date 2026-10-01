package edu.ucb.pablostify.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import edu.ucb.pablostify.signin.domain.usecase.AuthenticateUseCase

val domainModule = module {
    singleOf(::AuthenticateUseCase)
}