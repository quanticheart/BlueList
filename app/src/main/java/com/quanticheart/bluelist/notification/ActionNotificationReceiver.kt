package com.quanticheart.bluelist.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.quanticheart.bluelist.view.fragment.constants.ToDoConstants
import com.quanticheart.core.extentions.system.coroutineScopeLaunch
import com.quanticheart.core.system.broadcast.sendBroadcastAction
import com.quanticheart.core.system.notification.getNotificationID
import com.quanticheart.core.system.notification.notifyCancel
import com.quanticheart.domain.interaction.toDo.GetToDoUserCase
import com.quanticheart.domain.result.onSuccess
import org.koin.core.KoinComponent
import org.koin.core.inject

class ActionNotificationReceiver : BroadcastReceiver() {
    private val helper: MyHelper by lazy { MyHelper() }

    override fun onReceive(context: Context, intent: Intent) {
        helper.onReceive(context, intent)
    }
}

class MyHelper : KoinComponent {
    private val userCase: GetToDoUserCase by inject()

    fun onReceive(context: Context, intent: Intent) {
        val action = intent.getIntExtra(ToDoConstants.KEY_TO_DO_ID, -1)
        if (action != -1) {
            coroutineScopeLaunch {
                userCase.finish(action).onSuccess {
                    context.apply {
                        sendBroadcastAction(ToDoConstants.KEY_TO_DO_RELOAD)
                        notifyCancel(intent.getNotificationID())
                    }
                }
            }
        }
    }
}