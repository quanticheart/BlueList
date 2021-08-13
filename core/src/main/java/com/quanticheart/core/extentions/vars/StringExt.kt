@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.quanticheart.core.extentions.vars
/* ktlint-disable no-wildcard-imports */

import android.os.Build
import android.text.Html
import android.text.Spanned
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

@Suppress("DEPRECATION")
fun String?.toSpannedText(): Spanned? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(this)
    }
}