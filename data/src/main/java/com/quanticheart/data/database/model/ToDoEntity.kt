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
    @PrimaryKey(autoGenerate = true)
    var _id: Int = 0,
    val date: Long = Date().time,
    val title: String,
    val description: String,
    val priority: Int,
    val alarm: Long? = null,
    val check: Boolean = false
) : DomainMapperWithList<ToDo, ToDoSimple> {
    override fun mapToDomainModel() =
        ToDo(_id, Date(date), title, description, priority, alarm?.let { Date(it) }, check)

    override fun mapToDomainListModel() =
        ToDoSimple(_id, Date(date), priority, alarm?.let { Date(it) }, title, check, false)
}