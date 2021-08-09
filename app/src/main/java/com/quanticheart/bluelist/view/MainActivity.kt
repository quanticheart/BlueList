package com.quanticheart.bluelist.view

import android.content.Intent
import android.os.Bundle
import com.quanticheart.bluelist.R
import com.quanticheart.bluelist.view.fragment.constants.ToDoConstants
import com.quanticheart.bluelist.view.fragment.details.ToDoDetailsActivity
import com.quanticheart.core.base.activity.BaseActivity
import com.quanticheart.core.extentions.system.toPendingIntent
import com.quanticheart.core.system.notification.model.NotificationModel
import com.quanticheart.core.system.notification.sendNotification

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pendingIntent = Intent(this, ToDoDetailsActivity::class.java).apply {
            putExtra(ToDoConstants.KEY_TO_DO_ID, 14)
        }.toPendingIntent(this)

        val notification = NotificationModel(
            "TESEE",
            getString(R.string.msg_not_have_description),
            pendingIntent,
            null,
            getString(R.string.channel_name),
            getString(R.string.channel_description)
        )

        this.sendNotification(notification)
    }
}