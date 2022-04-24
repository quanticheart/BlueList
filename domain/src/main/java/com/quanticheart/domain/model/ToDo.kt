package com.quanticheart.domain.model

import java.util.Date

data class ToDo(
    val id: Int,
    val date: Date,
    val priority: Int,
    val alarm: Date?,
    val title: String,
    val check: Boolean,
    val finish: Boolean,
    val description: String?
)
