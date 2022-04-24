package com.quanticheart.core.extentions.system

fun String.toThrowable(cause: Throwable? = null) = Throwable(this, cause)
