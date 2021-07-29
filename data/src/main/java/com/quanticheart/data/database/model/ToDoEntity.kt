package com.quanticheart.data.database.model

/* ktlint-disable no-wildcard-imports */
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.quanticheart.data.mapper.DomainMapperWithList
import com.quanticheart.domain.model.ToDo
import com.quanticheart.domain.model.ToDoSimple
import java.util.*

@Entity
data class ToDoEntity(
    @PrimaryKey
    val id: String,
    val date: Long = Date().time,
    val title: String,
    val description: String,
    val color: String,
    val alarm: Long,
    val check: Boolean = false,
    val type: Int = 1
) : DomainMapperWithList<ToDo, ToDoSimple> {
    override fun mapToDomainModel() =
        ToDo(id, Date(date), title, description, color, Date(alarm), check, type)

    override fun mapToDomainListModel() =
        ToDoSimple(id, Date(date), color, Date(alarm), title, check, type)
}