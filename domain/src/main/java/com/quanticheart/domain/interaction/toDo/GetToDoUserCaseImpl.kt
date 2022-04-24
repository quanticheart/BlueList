package com.quanticheart.domain.interaction.toDo

import com.quanticheart.domain.model.ToDo
import com.quanticheart.domain.model.ToDoInsert
import com.quanticheart.domain.model.ToDoSimple
import com.quanticheart.domain.repository.ToDoRepository
import com.quanticheart.domain.result.ResultRepository

class GetToDoUserCaseImpl(private val repository: ToDoRepository) : GetToDoUserCase {
    override suspend fun getDetails(id: Int): ResultRepository<ToDo> =
        repository.getDetails(id)

    override suspend fun getList(): ResultRepository<List<ToDoSimple>> =
        repository.getList()

    override suspend fun delete(id: String): ResultRepository<Boolean> =
        repository.delete(id)

    override suspend fun insert(toDo: ToDoInsert): ResultRepository<Boolean> =
        repository.insert(toDo)

    override suspend fun finish(id: Int): ResultRepository<Boolean> =
        repository.finish(id)
}
