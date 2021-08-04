package com.quanticheart.core.extentions.commons.view

import android.widget.RadioGroup

fun RadioGroup.setCheckedListener(selectedID: (Int) -> Unit) {
    setOnCheckedChangeListener { _, i ->
        selectedID(i)
    }
    selectedID(checkedRadioButtonId)
}
