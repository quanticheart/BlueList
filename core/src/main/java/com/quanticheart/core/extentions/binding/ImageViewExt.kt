package com.quanticheart.core.extentions.binding

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.quanticheart.core.R
import com.quanticheart.core.extentions.vars.toDateLabel
import java.util.*

@BindingAdapter(value = ["priorityColor"])
fun View.priorityColor(priority: Int) {
    val color = when (priority) {
        1 -> R.color.priority_color_low
        2 -> R.color.priority_color_medium
        3 -> R.color.priority_color_high
        else -> R.color.priority_color_low
    }
    setBackgroundColor(context.resources.getColor(color, null))
}

@BindingAdapter(value = ["toDoDate"])
fun TextView.toDoDate(date: Date) {
    text = date.toDateLabel()
}
