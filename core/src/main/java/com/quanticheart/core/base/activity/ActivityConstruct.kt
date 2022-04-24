package com.quanticheart.core.base.activity

import androidx.viewbinding.ViewBinding
import com.quanticheart.core.base.viewModel.BaseViewModel

interface ActivityConstruct<viewModel : BaseViewModel, dataBinding : ViewBinding> {
    fun view(binding: dataBinding)

    fun viewModel(viewModel: viewModel)
}
