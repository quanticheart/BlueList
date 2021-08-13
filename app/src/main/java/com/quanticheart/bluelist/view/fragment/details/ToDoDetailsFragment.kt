package com.quanticheart.bluelist.view.fragment.details

import androidx.navigation.fragment.findNavController
import com.quanticheart.bluelist.R
import com.quanticheart.bluelist.databinding.FragmentToDoDetailsBinding
import com.quanticheart.bluelist.view.fragment.constants.ToDoConstants
import com.quanticheart.core.base.dialog.extentions.dialogAction
import com.quanticheart.core.base.dialog.extentions.dialogAlert
import com.quanticheart.core.base.fragment.BaseFragment
import com.quanticheart.core.extentions.commons.view.setSafeOnClickListener
import com.quanticheart.core.extentions.system.observeIsTrue
import com.quanticheart.core.extentions.system.observerNotNull
import com.quanticheart.core.extentions.system.toast
import com.quanticheart.core.system.broadcast.sendBroadcastAction

class ToDoDetailsFragment :
    BaseFragment<ToDoDetailsViewModel, FragmentToDoDetailsBinding>(R.layout.fragment_to_do_details) {

    override fun onFinishBindingView(binding: FragmentToDoDetailsBinding) {
        backToolbar(getString(R.string.label_toolbar_details)) {
            backList()
        }
        binding.viewModel = viewModel
    }

    override fun onFinishLoadViewModel(viewModel: ToDoDetailsViewModel) {
        viewModel.apply {
            loading.observerNotNull(this@ToDoDetailsFragment) {
                loading(it)
            }

            throwable.observerNotNull(this@ToDoDetailsFragment) {
                dialogAlert("Tivemos um problema", it.message, "OK") {
                    backList()
                }
            }

            details.observerNotNull(this@ToDoDetailsFragment) { toDo ->
                if (!toDo.check)
                    binding.btnFinishToDo.setSafeOnClickListener {
                        it.context.dialogAction(
                            it.context.resources.getString(R.string.label_title_finish_to_do),
                            it.context.resources.getString(
                                R.string.label_sub_msg_finish_to_do,
                                toDo.title
                            ),
                            it.context.resources.getString(R.string.label_finish)
                        ) {
                            finishToDo(toDo.id)
                        }
                    }
                else
                    binding.btnFinishToDo.setOnClickListener(null)
            }

            finishTodo.observeIsTrue(this@ToDoDetailsFragment) {
                sendBroadcastAction(ToDoConstants.KEY_TO_DO_RELOAD)
            }
        }

        arguments?.getInt(ToDoConstants.KEY_TO_DO_ID)?.let {
            viewModel.loadDetails(it)
        } ?: run {
            context?.toast(getString(R.string.msg_to_do_error_load_details))
            backList()
        }
    }

    private fun backList() {
        try {
            findNavController().navigate(R.id.toDoListFragment)
        } catch (e: Exception) {
            finish()
        }
    }
}