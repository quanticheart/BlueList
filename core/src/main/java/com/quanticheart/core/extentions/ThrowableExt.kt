package com.quanticheart.core.extentions

fun String.toThrowable(cause: Throwable? = null) = Throwable(this, cause)