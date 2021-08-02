@file:Suppress("unused")

package com.quanticheart.core.extentions.commons.libs.timber

import android.app.Application
import timber.log.Timber

fun Application.plantTimberTree(debug: Boolean) {
    if (debug)
        Timber.plant(CustomDebugTree())
    else
        Timber.plant(CustomReleaseTree())
}