package com.quanticheart.core.base.dialog

import androidx.databinding.ViewDataBinding

interface OnDialogActions<T : ViewDataBinding> {
    fun onViewFinishCreate(binding: T)
    fun onShow() {}
    fun onHide() {}
}