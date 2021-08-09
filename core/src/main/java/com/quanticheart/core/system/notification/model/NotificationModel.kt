package com.quanticheart.core.system.notification.model

import android.app.PendingIntent

data class NotificationModel(
    val title: String?,
    val messageBody: String?,
    var pendingIntent: PendingIntent? = null,
    val deepLink: String? = null,
    var channelName: String,
    var channelDescription: String
)