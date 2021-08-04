package com.quanticheart.bluelist.view.fragment.list

import com.quanticheart.bluelist.R
import com.quanticheart.bluelist.databinding.FragmentToDoListBinding
import com.quanticheart.bluelist.view.fragment.list.adapter.ToDoAdapter
import com.quanticheart.core.base.fragment.BaseFragment
import com.quanticheart.core.extentions.system.observeListNotEmpty
import com.quanticheart.core.generics.recyclerView.ListClickListener
import com.quanticheart.domain.model.ToDoSimple

class ToDoListFragment :
    BaseFragment<ToDoListViewModel, FragmentToDoListBinding>(R.layout.fragment_to_do_list),
    ListClickListener<ToDoSimple> {

    private val adapter by lazy { ToDoAdapter(this) }

    override fun onFinishBindingView(binding: FragmentToDoListBinding) {
        binding.viewModel = viewModel
        binding.adapter = adapter
    }

    override fun onFinishLoadViewModel(viewModel: ToDoListViewModel) {
        viewModel.apply {
            list.observeListNotEmpty(this@ToDoListFragment) {
                it.let(adapter::submitList)
            }
        }
    }

    override fun itemClickListener(item: ToDoSimple) {
    }

    override fun itemSelectedListener(item: ToDoSimple) {
        super.itemSelectedListener(item)
    }
}