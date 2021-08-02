package com.quanticheart.core.base.application

import android.app.Application
import com.quanticheart.core.extentions.commons.libs.timber.plantTimberTree

abstract class BaseApplication : Application() {

    abstract fun buildVariant(): Boolean

    override fun onCreate() {
        super.onCreate()

        plantTimberTree(buildVariant())
    }
}