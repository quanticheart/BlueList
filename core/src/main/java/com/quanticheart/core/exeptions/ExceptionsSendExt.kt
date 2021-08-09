@file:Suppress("unused")

package com.quanticheart.core.exeptions

import com.quanticheart.core.extentions.system.logE

fun Exception.sendException(msg: String? = "Error") {
    // send exception to firebase or other
    msg.logE()
}

fun Throwable.sendException(msg: String? = "Error") {
    // send exception to firebase or other
    msg.logE()
}