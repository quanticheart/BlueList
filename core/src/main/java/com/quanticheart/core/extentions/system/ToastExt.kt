package com.quanticheart.core.extentions.system

import android.content.Context
import android.widget.Toast

/**
 * Display the simple Toast message with the [Toast.LENGTH_SHORT] duration.
 *
 * @param message the message text resource.
 */
fun Context.toast(message: Any?): Toast = Toast
    .makeText(this, message?.toString() ?: "App Alert", Toast.LENGTH_SHORT)
    .apply {
        show()
    }
