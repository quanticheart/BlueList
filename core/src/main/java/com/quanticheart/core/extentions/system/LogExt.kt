package com.quanticheart.core.extentions.system

import timber.log.Timber

fun Any?.logI(tag: String? = null) {
    Timber.tag(tag ?: "INFO").i(this?.toString() ?: "Empty var")
}

fun Any?.logE(tag: String? = null) {
    Timber.tag(tag ?: "WARNING").e(this?.toString() ?: "Empty var")
}
