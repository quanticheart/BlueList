package com.quanticheart.core.base.recyclerView

interface ListClickListener<T : Any> {
    fun itemSelectedListener(item: T)
    fun itemDeleteListener(item: T, position: Int) {}
}
