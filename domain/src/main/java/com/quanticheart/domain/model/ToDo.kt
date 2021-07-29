package com.quanticheart.domain.model

import java.util.*

data class ToDo(
    val id: String,
    val date: Date,
    val title: String,
    val description: String,
    val color: String,
    val alarm: Date,
    val check: Boolean,
    val type: Int
)