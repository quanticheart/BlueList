package com.quanticheart.core.extentions.system.anko

import android.app.Activity
import android.app.PendingIntent
import android.app.Service
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import java.util.*

val randomID: Int
    get() {
        val rand = Random()
        return rand.nextInt(1000) + 500
    }

fun Intent.toPendingIntent(context: Context): PendingIntent {
    return this.let { intent ->
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        PendingIntent.getActivity(
            context,
            randomID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_ONE_SHOT
        )
    }
}

fun Intent.toBroadcastPendingIntent(context: Context): PendingIntent {
    return this.let { intent ->
        PendingIntent.getBroadcast(
            context,
            randomID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }
}

inline fun <reified T : Activity> Context.startActivity(vararg params: Pair<String, Any>) {
    AnkoInternals.internalStartActivity(this, T::class.java, params)
}

inline fun <reified T : Activity> AnkoContext<*>.startActivity(vararg params: Pair<String, Any>) {
    AnkoInternals.internalStartActivity(ctx, T::class.java, params)
}

inline fun <reified T : Activity> Fragment.startActivity(vararg params: Pair<String, Any>) {
    AnkoInternals.internalStartActivity(requireActivity(), T::class.java, params)
}

inline fun <reified T : Service> Context.startService(vararg params: Pair<String, Any>) {
    AnkoInternals.internalStartService(this, T::class.java, params)
}

inline fun <reified T : Service> AnkoContext<*>.startService(vararg params: Pair<String, Any>) {
    AnkoInternals.internalStartService(ctx, T::class.java, params)
}

inline fun <reified T : Service> Fragment.startService(vararg params: Pair<String, Any>) {
    AnkoInternals.internalStartService(requireActivity(), T::class.java, params)
}

inline fun <reified T : Any> Context.intentFor(vararg params: Pair<String, Any?>): Intent {
    return AnkoInternals.createIntent(this, T::class.java, params)
}

inline fun <reified T : Any> AnkoContext<*>.intentFor(vararg params: Pair<String, Any?>): Intent {
    return AnkoInternals.createIntent(ctx, T::class.java, params)
}

inline fun <reified T : Any> Fragment.intentFor(vararg params: Pair<String, Any?>): Intent {
    return AnkoInternals.createIntent(requireActivity(), T::class.java, params)
}

/**
 * Add the [Intent.FLAG_ACTIVITY_CLEAR_TASK] flag to the [Intent].
 *
 * @return the same intent with the flag applied.
 */
fun Intent.clearTask(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) }

/**
 * Add the [Intent.FLAG_ACTIVITY_CLEAR_TOP] flag to the [Intent].
 *
 * @return the same intent with the flag applied.
 */
fun Intent.clearTop(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) }

/**
 * Add the [Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS] flag to the [Intent].
 *
 * @return the same intent with the flag applied.
 */
fun Intent.excludeFromRecents(): Intent =
    apply { addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS) }

/**
 * Add the [Intent.FLAG_ACTIVITY_MULTIPLE_TASK] flag to the [Intent].
 *
 * @return the same intent with the flag applied.
 */
fun Intent.multipleTask(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK) }

/**
 * Add the [Intent.FLAG_ACTIVITY_NEW_TASK] flag to the [Intent].
 *
 * @return the same intent with the flag applied.
 */
fun Intent.newTask(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }

/**
 * Add the [Intent.FLAG_ACTIVITY_NO_ANIMATION] flag to the [Intent].
 *
 * @return the same intent with the flag applied.
 */
fun Intent.noAnimation(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION) }

/**
 * Add the [Intent.FLAG_ACTIVITY_NO_HISTORY] flag to the [Intent].
 *
 * @return the same intent with the flag applied.
 */
fun Intent.noHistory(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY) }

/**
 * Add the [Intent.FLAG_ACTIVITY_SINGLE_TOP] flag to the [Intent].
 *
 * @return the same intent with the flag applied.
 */
fun Intent.singleTop(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP) }

fun AnkoContext<*>.browse(url: String, newTask: Boolean = false) = ctx.browse(url, newTask)
fun Fragment.browse(url: String, newTask: Boolean = false) =
    requireActivity().browse(url, newTask)

fun Context.browse(url: String, newTask: Boolean = false): Boolean {
    return try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        if (newTask) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
        true
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        false
    }
}

fun AnkoContext<*>.share(text: String, subject: String = "") = ctx.share(text, subject)
fun Fragment.share(text: String, subject: String = "") =
    requireActivity().share(text, subject)

fun Context.share(text: String, subject: String = ""): Boolean {
    return try {
        val intent = Intent(android.content.Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(android.content.Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(intent, null))
        true
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        false
    }
}

fun AnkoContext<*>.email(email: String, subject: String = "", text: String = "") =
    ctx.email(email, subject, text)

fun Fragment.email(email: String, subject: String = "", text: String = "") =
    requireActivity().email(email, subject, text)

fun Context.email(email: String, subject: String = "", text: String = ""): Boolean {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse("mailto:")
    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
    if (subject.isNotEmpty())
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    if (text.isNotEmpty())
        intent.putExtra(Intent.EXTRA_TEXT, text)
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
        return true
    }
    return false

}

fun AnkoContext<*>.makeCall(number: String): Boolean = ctx.makeCall(number)
fun Fragment.makeCall(number: String): Boolean = requireActivity().makeCall(number)

fun Context.makeCall(number: String): Boolean {
    return try {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$number"))
        startActivity(intent)
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}

fun AnkoContext<*>.sendSMS(number: String, text: String = ""): Boolean =
    ctx.sendSMS(number, text)

fun Fragment.sendSMS(number: String, text: String = ""): Boolean =
    requireActivity().sendSMS(number, text)

fun Context.sendSMS(number: String, text: String = ""): Boolean {
    return try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:$number"))
        intent.putExtra("sms_body", text)
        startActivity(intent)
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}



