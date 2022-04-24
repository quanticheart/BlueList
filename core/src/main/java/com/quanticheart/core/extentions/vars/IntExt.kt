package com.quanticheart.core.extentions.vars

import android.content.res.Resources

val Int.toDp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
