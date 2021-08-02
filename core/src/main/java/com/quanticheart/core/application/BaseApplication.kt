package com.quanticheart.core.application

import android.app.Application
import com.quanticheart.core.application.timber.plantTimberTree

abstract class BaseApplication : Application() {

    abstract fun buildVariant(): Boolean

    override fun onCreate() {
        super.onCreate()

        plantTimberTree(buildVariant())
    }
}