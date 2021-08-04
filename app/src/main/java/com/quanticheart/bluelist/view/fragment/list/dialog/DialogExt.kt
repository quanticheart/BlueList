package com.quanticheart.bluelist.view.fragment.list.dialog

import androidx.fragment.app.Fragment
import com.quanticheart.domain.model.ToDoInsert

fun Fragment?.addToDo(callback: (ToDoInsert) -> Unit) {
    this?.activity?.let {
        DialogAddToDo(it, callback)
    }
}