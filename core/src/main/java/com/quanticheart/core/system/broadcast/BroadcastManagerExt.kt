package com.quanticheart.core.system.broadcast

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager

private var broadcastManager: LocalBroadcastManager? = null
private var reloadReceiver: BroadcastReceiver? = null

@Suppress("unused")
fun Activity.destroyBroadcastManager() {
    reloadReceiver?.let {
        broadcastManager?.unregisterReceiver(it)
        reloadReceiver = null
    }
}

fun Activity.createBroadcastManager(vararg actions: String, callback: (action: String) -> Unit) {
    val filter = IntentFilter().apply {
        actions.forEach {
            addAction(it)
        }
    }

    reloadReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            intent.action?.let { callback(it) }
        }
    }

    LocalBroadcastManager.getInstance(this).apply {
        broadcastManager = this
        reloadReceiver?.let { this.registerReceiver(it, filter) }
    }
}

fun Activity.sendBroadcastAction(action: String, extras: Bundle? = null) {
    val intent = Intent(action).apply { extras?.let { putExtras(it) } }
    LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
}
