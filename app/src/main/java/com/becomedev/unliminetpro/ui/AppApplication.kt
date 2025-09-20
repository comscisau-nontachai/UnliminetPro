package com.becomedev.unliminetpro.ui

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.becomedev.unliminetpro.di.appModule
import com.google.android.gms.ads.MobileAds
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

        // Initialize Google Mobile Ads SDK
        MobileAds.initialize(this) { initializationStatus ->
            // optional: ตรวจสถานะ
            Log.d("LOGD", "onCreate: ${initializationStatus.toString()}")
        }
    }

}