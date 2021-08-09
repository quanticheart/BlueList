package com.quanticheart.core.base.application

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.quanticheart.core.extentions.commons.libs.timber.plantTimberTree
import com.quanticheart.core.system.receivers.startMinuteService
import com.quanticheart.core.system.services.SERVICE_KEY_MINUTE

abstract class BaseApplication : Application() {

    abstract fun buildVariant(): Boolean
    abstract fun receiveMinuteAction()

    override fun onCreate() {
        super.onCreate()

        val b = AppReceive()
        val filter = IntentFilter()
        filter.addAction(SERVICE_KEY_MINUTE)
        registerReceiver(b, filter)

        plantTimberTree(buildVariant())

        startMinuteService()
    }

    inner class AppReceive : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                SERVICE_KEY_MINUTE -> receiveMinuteAction()
            }
        }
    }
}