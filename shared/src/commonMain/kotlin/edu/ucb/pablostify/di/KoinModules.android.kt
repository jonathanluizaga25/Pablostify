package edu.ucb.pablostify.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module


fun initKoinAndroid(context: Context) {
    startKoin {
        androidContext(context)
        androidLogger()
        modules(sharedModules())
    }
}
