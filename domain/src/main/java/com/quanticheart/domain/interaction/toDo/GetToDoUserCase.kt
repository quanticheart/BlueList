package com.quanticheart.domain.interaction.toDo

import com.quanticheart.domain.model.ToDo
import com.quanticheart.domain.model.ToDoInsert
import com.quanticheart.domain.model.ToDoSimple
import com.quanticheart.domain.result.ResultRepository

interface GetToDoUserCase {
    suspend fun getDetails(id: Int): ResultRepository<ToDo>
    suspend fun getList(): ResultRepository<List<ToDoSimple>>
    suspend fun delete(id: String): ResultRepository<Boolean>
    suspend fun insert(toDo: ToDoInsert): ResultRepository<Boolean>
    suspend fun finish(id: Int): ResultRepository<Boolean>
}
