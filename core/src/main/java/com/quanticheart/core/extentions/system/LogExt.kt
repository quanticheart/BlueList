package com.quanticheart.core.extentions.system

import timber.log.Timber

fun Any?.logI(tag: String? = null) {
    Timber.i(tag ?: "INFO", this?.toString() ?: "Empty var")
}

fun Any?.logE(tag: String? = null) {
    Timber.e(tag ?: "WARNING", this?.toString() ?: "Empty var")
}