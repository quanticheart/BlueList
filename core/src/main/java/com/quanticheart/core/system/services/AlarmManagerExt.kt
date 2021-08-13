@file:Suppress("unused")

package com.quanticheart.core.system.services

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import com.quanticheart.core.extentions.system.logE

@Suppress("unused")

private var pi: PendingIntent? = null
private var am: AlarmManager? = null
const val SERVICE_KEY_ALARM = "start_alarm"
const val SERVICE_KEY_MINUTE = "start_alarm_minute"
const val SERVICE_REQUEST_CODE = 4

/**
 * This alarm call in try download
 */
fun Context.startService(
    alarmBroadcast: Class<*>,
    minutesToStart: Int = 1,
    key: String? = null,
    requestCode: Int = SERVICE_REQUEST_CODE
) {
    val mIntent = Intent(this, alarmBroadcast)
    mIntent.putExtra(key ?: SERVICE_KEY_ALARM, true)
    am = getSystemService(ALARM_SERVICE) as AlarmManager
    pi = PendingIntent.getBroadcast(
        this,
        requestCode,
        mIntent,
        0
    )

    am?.setRepeating(
        AlarmManager.RTC_WAKEUP,
        System.currentTimeMillis() + (minutesToStart * 60000),
        (minutesToStart * 60000).toLong(),
        pi
    )
}

fun Context.cancelService(alarmBroadcast: Class<*>, requestCode: Int = SERVICE_REQUEST_CODE) {
    val mIntent = Intent(this, alarmBroadcast)
    val pendingIntent = PendingIntent.getBroadcast(this, requestCode, mIntent, 0)
    val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager?
    alarmManager?.let {
        "STOP Service Cnh".logE()
        it.cancel(pendingIntent)
    }
}
