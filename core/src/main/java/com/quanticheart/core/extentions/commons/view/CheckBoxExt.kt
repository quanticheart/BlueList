package com.quanticheart.core.extentions.commons.view

import android.widget.CheckBox

fun CheckBox.onCheckListener(checked: (Boolean) -> Unit) {
    setOnCheckedChangeListener { _, b ->
        checked(b)
    }
    checked(isChecked)
}
