package com.quanticheart.core.extentions.commons.libs.timber

import android.util.Log
import com.quanticheart.core.exeptions.sendException
import timber.log.Timber

class CustomReleaseTree : Timber.Tree() {
    override fun log(
        priority: Int,
        tag: String?,
        message: String,
        t: Throwable?
    ) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }
        t?.let {
            if (priority == Log.ERROR || priority == Log.WARN) {
                it.sendException()
            }
        }
    }
}
