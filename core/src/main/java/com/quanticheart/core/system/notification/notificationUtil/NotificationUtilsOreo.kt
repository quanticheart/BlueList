package com.quanticheart.core.system.notification.notificationUtil

import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

object NotificationUtilsOreo {
    /**
     * @param context - init
     * @return new Group FirebaseNotificationReceiver for oreo
     */
    fun createNewGroupNotification(context: Context, channelName: String): String {
        val groupId = "${channelName}_group"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannelGroup(
                NotificationChannelGroup(
                    groupId,
                    channelName
                )
            )
        }
        return groupId
    }

    /**
     * Set whether or not the notification should bypass Do Not Disturb mode (the INTERRUPTION_FILTER_PRIORITY value
     *
     * @param notificationChannel
     * @return true or false
     */
    fun getBypassDND(notificationChannel: NotificationChannel): Boolean {
        return getCanBypassDND(notificationChannel)
    }

    private fun getCanBypassDND(notificationChannel: NotificationChannel): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel.canBypassDnd()
        } else {
            false
        }
    }

    val channelLockScreenVisibility: Int
        get() = NotificationCompat.VISIBILITY_PUBLIC
}
