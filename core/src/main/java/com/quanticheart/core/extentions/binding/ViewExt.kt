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

@BindingAdapter(value = ["priorityText"])
fun TextView.priorityText(priority: Int) {
    val textPriority = when (priority) {
        1 -> context.getString(R.string.label_low_priority)
        2 -> context.getString(R.string.label_media_priority)
        3 -> context.getString(R.string.label_high_priority)
        else -> context.getString(R.string.label_low_priority)
    }
    text = textPriority
}

@BindingAdapter(value = ["visibilityNotNull"])
fun View.visibilityNotNull(obj: Any?) {
    post {
        visibility = if (obj != null) View.VISIBLE else View.GONE
    }
}

@BindingAdapter(value = ["toDoDate"])
fun TextView.toDoDate(date: Date?) {
    text = date?.toDateLabel()
}
