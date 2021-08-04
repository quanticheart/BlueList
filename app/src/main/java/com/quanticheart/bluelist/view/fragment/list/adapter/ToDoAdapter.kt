package com.quanticheart.bluelist.view.fragment.list.adapter

import com.quanticheart.bluelist.R
import com.quanticheart.bluelist.databinding.ToDoItemBinding
import com.quanticheart.core.generics.recyclerView.BindingListAdapter
import com.quanticheart.core.generics.recyclerView.ListClickListener
import com.quanticheart.domain.model.ToDoSimple

class ToDoAdapter(private val click: ListClickListener<ToDoSimple>) :
    BindingListAdapter<ToDoSimple, ToDoItemBinding>(R.layout.to_do_item) {

    override fun bind(binding: ToDoItemBinding, item: ToDoSimple, position: Int) {
        binding.todo = item
        binding.position = position
        binding.root.setOnClickListener {
            click.itemClickListener(item)
        }
    }
}
