package com.techs.mapapp

import android.app.Application
import com.techs.data.di.dataModule
import com.techs.data.di.networkModule
import com.techs.mapapp.di.pinModule
import com.techs.mapapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    dataModule,
                    pinModule,
                    viewModelModule
                )
            )
        }
        Timber.plant(DebugTree())
    }

}