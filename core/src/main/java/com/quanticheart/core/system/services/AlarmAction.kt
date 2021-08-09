package com.quanticheart.core.system.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmAction : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.getBooleanExtra(SERVICE_KEY_ALARM, false)) {
            context.sendBroadcast(Intent(SERVICE_KEY_MINUTE))
        }
    }
}
