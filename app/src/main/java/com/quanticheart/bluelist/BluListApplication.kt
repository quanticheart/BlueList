package com.quanticheart.bluelist

import android.app.Application
import com.quanticheart.data.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BluListApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG) androidLogger(Level.DEBUG)
            androidContext(this@BluListApplication)
            modules(dataModules)
        }
    }

    val dataModules = listOf(databaseModule)
}