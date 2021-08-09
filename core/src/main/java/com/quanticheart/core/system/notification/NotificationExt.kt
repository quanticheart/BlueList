package com.quanticheart.core.system.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import androidx.core.app.NotificationCompat
import com.quanticheart.core.exeptions.sendException
import com.quanticheart.core.system.notification.config.NotificationConfig.colorNotification
import com.quanticheart.core.system.notification.config.NotificationConfig.getBigTextStyle
import com.quanticheart.core.system.notification.config.NotificationConfig.getImageStyle
import com.quanticheart.core.system.notification.config.NotificationConfig.iconNotification
import com.quanticheart.core.system.notification.config.NotificationConfig.priorityNotification
import com.quanticheart.core.system.notification.config.NotificationConfig.randomIDNotification
import com.quanticheart.core.system.notification.config.channelLightColor
import com.quanticheart.core.system.notification.model.NotificationModel
import com.quanticheart.core.system.notification.notificationUtil.NotificationUtilsOreo.channelLockScreenVisibility
import com.quanticheart.core.system.notification.notificationUtil.NotificationUtilsOreo.createNewGroupNotification
import com.quanticheart.core.system.notification.notificationUtil.NotificationUtilsOreo.getBypassDND

fun Context.sendNotification(data: NotificationModel) {
    try {
        createNotification(data, null)
    } catch (e: Exception) {
        e.sendException("Error on launch notification")
    }
}

private fun Context.createNotification(data: NotificationModel, img: Bitmap?) {
    showNotification(
        createNotificationManager(data.channelName, data.channelDescription),
        createNotificationBuilder(
            data.pendingIntent,
            data.title,
            data.messageBody,
            data.channelName,
            img
        )
    )
}

/**
// ** Show FirebaseNotificationReceiver
//
 */
/**
 * @param notificationManager - sys manager
 * @param notification - new notification
 */
private fun showNotification(
    notificationManager: NotificationManager,
    notification: Notification
) {
    notificationManager.notify(randomIDNotification(), notification)
}
/**
//
// ** Create FirebaseNotificationReceiver Builder
//
 */
/**
 * @param Title - title
 * @param messageBody - msg notification
 * @return new notification
 */
@Suppress("DEPRECATION")
private fun Context.createNotificationBuilder(
    pendingIntent: PendingIntent?,
    Title: String?,
    messageBody: String?,
    channelName: String,
    image: Bitmap? = null
): Notification {
    val notificationBuilder: NotificationCompat.Builder =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(this, "${channelName}_id")
        } else {
            NotificationCompat.Builder(this).setPriority(priorityNotification)
        }

    notificationBuilder
        .setSmallIcon(iconNotification)
        .setColor(colorNotification(this))
        .setContentTitle(Title)
        .setContentText(messageBody)
        .setAutoCancel(true)
        .setWhen(System.currentTimeMillis() + 1000)
        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        .setContentIntent(pendingIntent)

    image?.let {
        notificationBuilder
            .setStyle(getImageStyle(image, messageBody))
    } ?: run {
        if (messageBody?.length ?: 0 > 30)
            notificationBuilder.setStyle(getBigTextStyle(messageBody))
    }

    return notificationBuilder.build()
}

/**
//
// ** Create FirebaseNotificationReceiver Manager
//
 */
/**
 * @return - new NotificationManager
 */
private fun Context.createNotificationManager(
    channelName: String,
    channelDescription: String
): NotificationManager {

    val notificationManager =
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationChannel =
            NotificationChannel(
                "${channelName}_id",
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
        notificationChannel.description = channelDescription
        notificationChannel.enableLights(false)
        notificationChannel.lightColor = channelLightColor
        notificationChannel.setShowBadge(false)
        notificationChannel.enableVibration(true)
//            notificationChannel.vibrationPattern = vibration
        notificationChannel.setBypassDnd(getBypassDND(notificationChannel))
        notificationChannel.lockscreenVisibility = channelLockScreenVisibility
        notificationChannel.group = createNewGroupNotification(this, channelName)
        notificationChannel.vibrationPattern =
            longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        
        notificationManager.createNotificationChannel(notificationChannel)
    }
    return notificationManager
}