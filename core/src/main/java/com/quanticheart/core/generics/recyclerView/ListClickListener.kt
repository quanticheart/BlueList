@file:Suppress("unused")

package com.quanticheart.core.generics.recyclerView

interface ListClickListener<T : Any> {
    fun itemClickListener(item: T)
    fun itemSelectedListener(item: T) {}
    fun itemDeleteListener(item: T, position: Int) {}
}
