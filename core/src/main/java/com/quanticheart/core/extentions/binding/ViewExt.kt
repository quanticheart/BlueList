package com.quanticheart.core.extentions.binding

import android.widget.CheckBox

fun CheckBox.onCheckListener(checked: (Boolean) -> Unit) {
    setOnCheckedChangeListener { _, b ->
        checked(b)
    }
    checked(isChecked)
}
