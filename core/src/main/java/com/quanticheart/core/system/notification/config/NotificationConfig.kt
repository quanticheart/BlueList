@file:Suppress("UNUSED_PARAMETER", "unused")

package com.quanticheart.core.system.notification.config
/* ktlint-disable no-wildcard-imports */
import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import androidx.core.app.NotificationCompat
import com.quanticheart.core.R
import java.io.File
import java.util.*

/**
//
// ** Utils for Oreo
//
 */
const val NOTIFICATION_ID = "notification_id_key"
const val channelName = "Informações"

const val channelDescription = "Informações sobre o app."

const val channelLightColor = Color.YELLOW

internal object NotificationConfig {

    fun colorNotification(context: Context) = context.getColor(R.color.app_primary)

    val iconNotification = R.drawable.ic_baseline_arrow_back

    fun randomIDNotification(): Int {
        val rand = Random()
        return rand.nextInt(1000) + 500
    }

    @SuppressLint("InlinedApi")
    const val priorityNotification = NotificationManager.IMPORTANCE_HIGH

    /**
    //
    // ** FirebaseNotificationReceiver Style
    //
     */
// getStyle BigText
    fun getBigTextStyle(mMessage: String?): NotificationCompat.BigTextStyle {
        return NotificationCompat.BigTextStyle().bigText(mMessage)
    }

    // getStyle Image
    fun getImageStyle(bitmap: Bitmap?, msgBody: String?): NotificationCompat.BigPictureStyle {
        return NotificationCompat.BigPictureStyle().bigPicture(bitmap)
    }

    /**
    //
    // ** Make Sound System
    // https://stackoverflow.com/questions/53913251/different-notification-sound-not-working-in-oreo
     */
//    fun getSoundNotification(context: Context) = context.makeUriByRAW(R.raw.notify)

    private fun Context.makeUriByRAW(rawSound: Int): Uri {
//        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.packageName + "/" + rawSound)
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + packageName + "/" + rawSound)
    }

    fun Context.getRawUri(filename: String): Uri? {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + File.pathSeparator + File.separator + packageName.toString() + "/raw/" + filename)
    }
}