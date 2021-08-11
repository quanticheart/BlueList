package com.quanticheart.core.generics.liveData

import androidx.lifecycle.MutableLiveData

class BooleanLiveData(status: Boolean = false) : MutableLiveData<Boolean>() {
    init {
        value = status
    }
}

fun BooleanLiveData.setTrueClean() {
    this.value = true
    this.value = null
}
