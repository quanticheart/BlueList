package com.quanticheart.data.database.utils

/* ktlint-disable no-wildcard-imports */

data class WhereNotNull(
    val column: String
) {
    val operator: String = "IS NOT"
    val condition: String = "NULL"
}