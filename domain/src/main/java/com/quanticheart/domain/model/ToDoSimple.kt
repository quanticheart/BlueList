package com.quanticheart.domain.model

import java.util.*

data class ToDoSimple(
    val id: Int,
    val date: Date,
    val color: String,
    val alarm: Date,
    val title: String,
    val check: Boolean,
    val type: Int
)