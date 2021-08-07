package com.quanticheart.core.extentions.system

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

inline fun <E, T : Iterable<E>> LiveData<T>.observeListNotEmpty(
    lifecycleOwner: LifecycleOwner,
    crossinline callback: (data: List<E>) -> Unit
) {
    this.observe(lifecycleOwner, {
        it?.let {
            val l = it.toList()
            if (l.isNotEmpty())
                callback(l)
        }
    })
}

inline fun <T> LiveData<T>.observerNotNull(
    lifecycleOwner: LifecycleOwner,
    crossinline callback: (data: T) -> Unit
) {
    this.observe(lifecycleOwner, {
        it?.let { t ->
            callback(t)
        }
    })
}
