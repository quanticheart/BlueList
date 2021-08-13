package com.quanticheart.core.system.notification.model

import android.app.PendingIntent

data class NotificationActionModel(
    val title: String,
    val img: Int,
    var pendingIntent: PendingIntent,
)