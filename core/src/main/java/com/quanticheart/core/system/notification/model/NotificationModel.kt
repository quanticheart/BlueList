package com.quanticheart.core.system.notification.model

import android.content.Intent

data class NotificationModel(
    val title: String?,
    val messageBody: String?,
    var intent: Intent? = null,
    val deepLink: String? = null,
    var channelName: String,
    var channelDescription: String,
    var action: NotificationActionModel? = null
)