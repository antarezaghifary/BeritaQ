package com.test.beritaq

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.test.beritaq.source.berita.repositoryModule
import com.test.beritaq.source.network.networkModule
import com.test.beritaq.ui.home.mainModule
import com.test.beritaq.ui.home.mainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class BeritaQ : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.e(" run base application")
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@BeritaQ)
            modules(
                networkModule,
                repositoryModule,
                mainViewModel,
                mainModule
            )
        }
    }
}