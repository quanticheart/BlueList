package com.quanticheart.bluelist.view.fragment.details

import com.quanticheart.bluelist.R
import com.quanticheart.bluelist.databinding.FragmentToDoDetailsBinding
import com.quanticheart.core.base.fragment.BaseFragment

class ToDoDetailsFragment :
    BaseFragment<ToDoDetailsViewModel, FragmentToDoDetailsBinding>(R.layout.fragment_to_do_details) {

    override fun onFinishBindingView(binding: FragmentToDoDetailsBinding) {
        binding.text2
    }

    override fun onFinishLoadViewModel(viewModel: ToDoDetailsViewModel) {
    }
}