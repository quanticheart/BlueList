package com.quanticheart.bluelist.application

import android.content.Intent
import com.quanticheart.bluelist.BuildConfig
import com.quanticheart.bluelist.R
import com.quanticheart.bluelist.view.di.appModule
import com.quanticheart.bluelist.view.fragment.constants.ToDoConstants
import com.quanticheart.bluelist.view.fragment.details.ToDoDetailsActivity
import com.quanticheart.core.base.application.BaseApplication
import com.quanticheart.core.exeptions.sendException
import com.quanticheart.core.extentions.system.coroutineScopeLaunch
import com.quanticheart.core.extentions.system.toPendingIntent
import com.quanticheart.core.system.notification.model.NotificationModel
import com.quanticheart.core.system.notification.sendNotification
import com.quanticheart.data.di.databaseModule
import com.quanticheart.data.di.repositoryModule
import com.quanticheart.domain.di.interactionModule
import com.quanticheart.domain.interaction.notification.GetNotificationUserCase
import com.quanticheart.domain.result.onFailure
import com.quanticheart.domain.result.onSuccess
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BluListApplication : BaseApplication() {

    private val userCase: GetNotificationUserCase by inject()

    override fun buildVariant(): Boolean = BuildConfig.DEBUG

    override fun receiveMinuteAction() {
        coroutineScopeLaunch {
            userCase.getNotificationList().onSuccess {
                it.forEach { item ->
                    val pendingIntent = Intent(this, ToDoDetailsActivity::class.java).apply {
                        putExtra(ToDoConstants.KEY_TO_DO_ID, item.id)
                    }.toPendingIntent(this)

                    val notification = NotificationModel(
                        item.title,
                        item.description ?: getString(R.string.msg_not_have_description),
                        pendingIntent,
                        null,
                        getString(R.string.channel_name),
                        getString(R.string.channel_description)
                    )

                    this.sendNotification(notification)
                }
            }.onFailure {
                it.throwable.sendException("Error on load notification list")
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG) androidLogger(Level.DEBUG)
            androidContext(this@BluListApplication)
            modules(appModules + dataModules + domainModules)
        }
    }

    val appModules = listOf(appModule)
    val dataModules = listOf(databaseModule, repositoryModule)
    val domainModules = listOf(interactionModule)
}