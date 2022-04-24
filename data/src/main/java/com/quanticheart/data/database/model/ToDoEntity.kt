package com.quanticheart.data.database.model

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
    val description: String?,
    val priority: Int,
    val alarm: Long? = null,
    var check: Boolean = false,
    var finish: Long? = null
) : DomainMapperWithList<ToDo, ToDoSimple> {
    override fun mapToDomainModel() =
        ToDo(_id, Date(date), priority, alarm?.let { Date(it) }, title, check, check, description)

    override fun mapToDomainListModel() =
        ToDoSimple(_id, Date(date), priority, alarm?.let { Date(it) }, title, check, check)
}
