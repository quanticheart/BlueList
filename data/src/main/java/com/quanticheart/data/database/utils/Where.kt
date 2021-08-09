package com.quanticheart.data.database.utils

/* ktlint-disable no-wildcard-imports */

data class Where(
    val column: String,
    val operator: String,
    val condition: String
)