package com.quanticheart.core.extentions.system

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

val randomID: Int
    get() {
        val rand = Random()
        return rand.nextInt(1000) + 500
    }

fun Intent.toPendingIntent(context: Context): PendingIntent {
    return this.let { intent ->
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        PendingIntent.getActivity(
            context,
            randomID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_ONE_SHOT
        )
    }
}

fun Intent.toBroadcastPendingIntent(context: Context): PendingIntent {
    return this.let { intent ->
        PendingIntent.getBroadcast(
            context,
            randomID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }
}
