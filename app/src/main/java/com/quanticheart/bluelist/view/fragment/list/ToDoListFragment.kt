package com.quanticheart.bluelist.view.fragment.list

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.quanticheart.bluelist.R
import com.quanticheart.bluelist.databinding.FragmentToDoListBinding
import com.quanticheart.bluelist.view.fragment.constants.ToDoConstants
import com.quanticheart.bluelist.view.fragment.list.adapter.ToDoAdapter
import com.quanticheart.bluelist.view.fragment.list.dialog.addToDo
import com.quanticheart.core.base.dialog.extentions.dialogAlert
import com.quanticheart.core.base.fragment.BaseFragment
import com.quanticheart.core.extentions.commons.view.getColor
import com.quanticheart.core.extentions.commons.view.setSafeOnClickListener
import com.quanticheart.core.extentions.system.observeListNotEmpty
import com.quanticheart.core.extentions.system.observerNotNull
import com.quanticheart.core.generics.recyclerView.ListClickListener
import com.quanticheart.core.system.broadcast.createBroadcastManager
import com.quanticheart.domain.model.ToDoSimple

class ToDoListFragment :
    BaseFragment<ToDoListViewModel, FragmentToDoListBinding>(),
    ListClickListener<ToDoSimple> {

    private val adapter by lazy { ToDoAdapter(this) }

    override fun view(binding: FragmentToDoListBinding) {
        binding.viewModel = viewModel
        binding.adapter = adapter
        binding.addMoreToDo.setSafeOnClickListener {
            addToDo {
                viewModel?.addToDo(it)
            }
        }

        binding.refresh.apply {
            setColorSchemeColors(getColor(R.color.app_primary))
            setOnRefreshListener {
                viewModel?.loadToDoList()
                isRefreshing = false
            }
        }

        binding.theme.setSafeOnClickListener {
            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_YES ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Configuration.UI_MODE_NIGHT_NO ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }

    override fun viewModel(viewModel: ToDoListViewModel) {
        viewModel.apply {
            loading.observerNotNull(this@ToDoListFragment) {
                loading(it)
            }

            throwable.observerNotNull(this@ToDoListFragment) {
                dialogAlert("Tivemos um problema", it?.message, "OK")
            }

            list.observeListNotEmpty(this@ToDoListFragment) {
                it.let(adapter::submitList)
                binding.flipperList.displayedChild = 1
            }
        }
    }

    override fun itemClickListener(item: ToDoSimple) {
        findNavController().navigate(
            R.id.toDoDetailsFragment,
            Bundle().apply {
                putInt(ToDoConstants.KEY_TO_DO_ID, item.id)
            }
        )
    }

    override fun itemSelectedListener(item: ToDoSimple) {
        super.itemSelectedListener(item)
        viewModel?.finishToDo(item.id)
    }

    override fun onResume() {
        super.onResume()
        createBroadcastManager(ToDoConstants.KEY_TO_DO_RELOAD) {
            when (it) {
                ToDoConstants.KEY_TO_DO_RELOAD -> viewModel?.loadToDoList()
            }
        }
    }
}
