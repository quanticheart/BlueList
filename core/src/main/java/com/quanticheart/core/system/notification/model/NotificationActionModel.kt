package com.quanticheart.core.system.notification.model

import android.content.Intent

data class NotificationActionModel(
    val title: String,
    val img: Int,
    var intent: Intent
)