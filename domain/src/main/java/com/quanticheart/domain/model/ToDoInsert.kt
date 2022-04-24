package com.quanticheart.domain.model

import java.util.Date

data class ToDoInsert(
    val date: Date,
    val title: String,
    val description: String?,
    val priority: Int,
    val alarm: Date?
)
