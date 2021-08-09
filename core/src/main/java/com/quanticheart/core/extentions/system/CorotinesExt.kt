package com.quanticheart.core.extentions.system
/* ktlint-disable no-wildcard-imports */
import kotlinx.coroutines.*

suspend fun <T, E> T.coroutineIO(block: suspend (T) -> E): E {
    return withContext(Dispatchers.IO) {
        block(this@coroutineIO)
    }
}

fun <T, E> T.coroutineScopeLaunch(block: suspend (T) -> E): Job {
    return CoroutineScope(Dispatchers.Main).launch {
        block(this@coroutineScopeLaunch)
    }
}
