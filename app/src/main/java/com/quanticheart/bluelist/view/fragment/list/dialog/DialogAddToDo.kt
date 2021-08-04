package com.quanticheart.bluelist.view.fragment.list.dialog

import android.app.Activity
import com.quanticheart.bluelist.R
import com.quanticheart.bluelist.databinding.DialogAddToDoBinding
import com.quanticheart.core.base.dialog.BaseBottonSheetFragmentDialog
import com.quanticheart.core.extentions.system.toast
import com.quanticheart.domain.model.ToDoInsert

class DialogAddToDo(
    private val activity: Activity,
    private val callback: (ToDoInsert) -> Unit
) :
    BaseBottonSheetFragmentDialog<DialogAddToDoBinding>(activity, R.layout.dialog_add_to_do) {

    override fun onViewFinishCreate(binding: DialogAddToDoBinding) {
        binding.btn.setOnClickListener {
            val title = binding.title.text?.toString()

            if (title.isNullOrEmpty()) {
                activity.toast("Digite todos os campos")
            } else {
                callback(ToDoInsert(title))
            }
        }

        binding.cancel.setOnClickListener { safeHide() }
    }
}