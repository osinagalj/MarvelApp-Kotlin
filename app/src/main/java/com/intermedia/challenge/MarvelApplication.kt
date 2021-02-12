package com.intermedia.challenge

import android.app.Application

import com.intermedia.challenge.di.businessModule
import com.intermedia.challenge.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MarvelApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MarvelApplication)
            modules(listOf(networkModule, businessModule))
        }
    }
}