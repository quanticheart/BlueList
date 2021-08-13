package com.quanticheart.core.system.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.core.app.NotificationCompat
import com.quanticheart.core.exeptions.sendException
import com.quanticheart.core.extentions.system.toBroadcastPendingIntent
import com.quanticheart.core.extentions.system.toPendingIntent
import com.quanticheart.core.system.notification.config.NOTIFICATION_ID
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
    val id = randomIDNotification()
    try {
        showNotification(
            createNotificationManager(data.channelName, data.channelDescription),
            createNotificationBuilder(data, null, id),
            id
        )
    } catch (e: Exception) {
        e.sendException("Error on launch notification")
    }
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
    notification: Notification,
    notificationID: Int
) {
    notificationManager.notify(notificationID, notification)
}

/**
//
// ** Create FirebaseNotificationReceiver Builder
//
 */
@Suppress("DEPRECATION")
private fun Context.createNotificationBuilder(
    data: NotificationModel,
    image: Bitmap?,
    notificationID: Int
): Notification {
    val notificationBuilder: NotificationCompat.Builder =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(this, "${data.channelName}_id")
        } else {
            NotificationCompat.Builder(this).setPriority(priorityNotification)
        }

    notificationBuilder
        .setSmallIcon(iconNotification)
        .setColor(colorNotification(this))
        .setContentTitle(data.title)
        .setContentText(data.messageBody)
        .setAutoCancel(true)
        .setWhen(System.currentTimeMillis() + 1000)
        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

    data.intent?.let {
        it.putExtra(NOTIFICATION_ID, notificationID)
        notificationBuilder.setContentIntent(it.toPendingIntent(this))
    }

    data.action?.let {
        it.intent.putExtra(NOTIFICATION_ID, notificationID)
        notificationBuilder.addAction(it.img, it.title, it.intent.toBroadcastPendingIntent(this))
    }

    image?.let {
        notificationBuilder
            .setStyle(getImageStyle(image, data.messageBody))
    } ?: run {
        if (data.messageBody?.length ?: 0 > 30)
            notificationBuilder.setStyle(getBigTextStyle(data.messageBody))
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
        getSystemService(NOTIFICATION_SERVICE) as NotificationManager

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

fun Context.notifyCancel(notificationID: Int) {
    (getSystemService(NOTIFICATION_SERVICE) as NotificationManager?)?.cancel(notificationID)
    val it = Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
    sendBroadcast(it)
}

fun Intent.getNotificationID(): Int {
    return if (hasExtra(NOTIFICATION_ID))
        getIntExtra(NOTIFICATION_ID, -1)
    else
        -1
}
