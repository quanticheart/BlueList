@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.quanticheart.core.extentions.vars

import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(pattern: String): Date? {
    val formato = SimpleDateFormat(pattern, Locale.getDefault())
    return formato.parse(this)
}

fun String.toDateLabel(patternFrom: String, patternTo: String): String? {
    val to = SimpleDateFormat(patternTo, Locale.getDefault())
    val from = SimpleDateFormat(patternFrom, Locale.getDefault())
    val convertedCurrentDate = from.parse(this)
    return to.format(convertedCurrentDate)
}