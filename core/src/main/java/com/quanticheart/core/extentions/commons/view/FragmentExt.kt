package com.quanticheart.core.extentions.commons.view

import androidx.fragment.app.Fragment

fun Fragment?.getColor(resInt: Int): Int {
    return this?.context?.resources?.getColor(resInt, null) ?: 0
}
