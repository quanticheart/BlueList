package com.quanticheart.bluelist.view.fragment.list

import com.quanticheart.bluelist.R
import com.quanticheart.bluelist.databinding.FragmentToDoListBinding
import com.quanticheart.core.base.fragment.BaseFragment

class ToDoListFragment :
    BaseFragment<ToDoListViewModel, FragmentToDoListBinding>(R.layout.fragment_to_do_list) {

    override fun onFinishBindingView(binding: FragmentToDoListBinding) {
        binding.text
    }

    override fun onFinishLoadViewModel(viewModel: ToDoListViewModel) {
    }
}