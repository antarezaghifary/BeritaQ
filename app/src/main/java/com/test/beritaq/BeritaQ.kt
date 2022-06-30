package com.test.beritaq

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import timber.log.Timber

class BeritaQ : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.e(" run base application")
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}