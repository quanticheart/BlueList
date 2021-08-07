@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.quanticheart.core.extentions.vars

import java.text.SimpleDateFormat
import java.util.*

const val PATTERN_DATE = "yyyy/MM/dd HH:mm:ss"
const val PATTERN_DATE_FORMAT = "dd 'de' MMMM 'de' yyyy 'as' HH:mm"

fun String.toDate(pattern: String = PATTERN_DATE): Date? {
    val formato = SimpleDateFormat(pattern, Locale.getDefault())
    return formato.parse(this)
}

fun String.toDateLabel(
    patternFrom: String = PATTERN_DATE,
    patternTo: String = PATTERN_DATE_FORMAT
): String? {
    val to = SimpleDateFormat(patternTo, Locale.getDefault())
    val from = SimpleDateFormat(patternFrom, Locale.getDefault())
    val convertedCurrentDate = from.parse(this)
    return to.format(convertedCurrentDate)
}

fun Date.toDateLabel(patternTo: String = PATTERN_DATE_FORMAT): String? {
    val to = SimpleDateFormat(patternTo, Locale.getDefault())
    return to.format(this)
}