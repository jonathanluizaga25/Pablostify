package edu.ucb.pablostify.di

// NO DEBES ELIMINAR EL PAQUETE DE TU APLICACION


import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        module {
            sharedModules()
        }
    }
}
