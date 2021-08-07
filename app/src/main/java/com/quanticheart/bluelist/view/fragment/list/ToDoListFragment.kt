package com.quanticheart.bluelist.view.fragment.list

import com.quanticheart.bluelist.R
import com.quanticheart.bluelist.databinding.FragmentToDoListBinding
import com.quanticheart.bluelist.view.fragment.list.adapter.ToDoAdapter
import com.quanticheart.bluelist.view.fragment.list.dialog.addToDo
import com.quanticheart.core.base.dialog.extentions.dialogAlert
import com.quanticheart.core.base.fragment.BaseFragment
import com.quanticheart.core.extentions.commons.view.getColor
import com.quanticheart.core.extentions.commons.view.setSafeOnClickListener
import com.quanticheart.core.extentions.system.observeListNotEmpty
import com.quanticheart.core.extentions.system.observerNotNull
import com.quanticheart.core.generics.recyclerView.ListClickListener
import com.quanticheart.domain.model.ToDoSimple

class ToDoListFragment :
    BaseFragment<ToDoListViewModel, FragmentToDoListBinding>(R.layout.fragment_to_do_list),
    ListClickListener<ToDoSimple> {

    private val adapter by lazy { ToDoAdapter(this) }

    override fun onFinishBindingView(binding: FragmentToDoListBinding) {
        binding.viewModel = viewModel
        binding.adapter = adapter
        binding.addMoreToDo.setSafeOnClickListener {
            addToDo {
                viewModel?.addToDo(it)
            }
        }

        binding.refresh.apply {
            setColorSchemeColors(getColor(R.color.purple_500))
            setOnRefreshListener {
                viewModel?.loadToDoList()
                isRefreshing = false
            }
        }
    }

    override fun onFinishLoadViewModel(viewModel: ToDoListViewModel) {
        viewModel.apply {
            loading.observerNotNull(this@ToDoListFragment) {
                loading(it)
            }

            throwable.observerNotNull(this@ToDoListFragment) {
                dialogAlert("Tivemos um problema", it.message, "OK")
            }

            list.observeListNotEmpty(this@ToDoListFragment) {
                it.let(adapter::submitList)
                binding.flipperList.displayedChild = 1
            }
        }
    }

    override fun itemClickListener(item: ToDoSimple) {
    }

    override fun itemSelectedListener(item: ToDoSimple) {
        super.itemSelectedListener(item)
        viewModel?.finishToDo(item.id)
    }
}