package com.quanticheart.domain.model

import java.util.Date

data class ToDoSimple(
    val id: Int,
    val date: Date,
    val priority: Int,
    val alarm: Date?,
    val title: String,
    val check: Boolean,
    val finish: Boolean
)
