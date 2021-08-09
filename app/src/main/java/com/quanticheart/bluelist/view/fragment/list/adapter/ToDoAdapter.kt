package com.quanticheart.bluelist.view.fragment.list.adapter

import com.quanticheart.bluelist.R
import com.quanticheart.bluelist.databinding.ToDoItemBinding
import com.quanticheart.core.base.dialog.extentions.dialogAction
import com.quanticheart.core.extentions.commons.view.setSafeOnClickListener
import com.quanticheart.core.generics.recyclerView.BindingListAdapter
import com.quanticheart.core.generics.recyclerView.ListClickListener
import com.quanticheart.domain.model.ToDoSimple

class ToDoAdapter(private val click: ListClickListener<ToDoSimple>) :
    BindingListAdapter<ToDoSimple, ToDoItemBinding>(R.layout.to_do_item) {

    override fun bind(binding: ToDoItemBinding, item: ToDoSimple, position: Int) {
        binding.todo = item
        binding.position = position
        binding.root.setSafeOnClickListener {
            click.itemClickListener(item)
        }
        if (!item.check)
            binding.btnFinishToDo.setSafeOnClickListener {
                it.context.dialogAction(
                    it.context.resources.getString(R.string.label_title_finish_to_do),
                    it.context.resources.getString(R.string.label_sub_msg_finish_to_do, item.title),
                    it.context.resources.getString(R.string.label_finish)
                ) {
                    click.itemSelectedListener(item)
                }
            }
    }
}
