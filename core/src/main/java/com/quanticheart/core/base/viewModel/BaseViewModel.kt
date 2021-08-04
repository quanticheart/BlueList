@file:Suppress("MemberVisibilityCanBePrivate")

package com.quanticheart.core.base.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    private val _loading = MutableLiveData<Boolean>().apply { value = false }
    val loading: LiveData<Boolean>
        get() = _loading

    private val _throwable = MutableLiveData<Throwable>()
    val throwable: LiveData<Throwable>
        get() = _throwable

    protected fun showLoading() {
        _loading.value = true
    }

    protected fun hideLoading() {
        _loading.value = false
    }

    protected fun Throwable.alertError() {
        _throwable.value = this
        _throwable.value = null
    }

    protected fun coroutineScopeLaunchLoading(block: suspend (CoroutineScope) -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            showLoading()
            block(this)
            hideLoading()
        }
    }
}
