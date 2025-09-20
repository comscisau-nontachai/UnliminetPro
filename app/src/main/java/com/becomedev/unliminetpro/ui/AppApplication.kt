package com.becomedev.unliminetpro.ui

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.becomedev.unliminetpro.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Koin
        startKoin {
            androidContext(applicationContext)
            modules(listOf(appModule))
            androidLogger()
        }

        /*disable night mode*/
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

}