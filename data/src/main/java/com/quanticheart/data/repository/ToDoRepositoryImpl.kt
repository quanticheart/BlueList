package com.quanticheart.data.repository

/* ktlint-disable no-wildcard-imports */
import com.quanticheart.core.extentions.system.coroutineIO
import com.quanticheart.data.database.dao.ToDoDao
import com.quanticheart.data.database.model.ToDoEntity
import com.quanticheart.data.database.utils.FunctionsCoroutine.getQueryDeleteTableByID
import com.quanticheart.data.database.utils.FunctionsCoroutine.getQuerySelectAll
import com.quanticheart.data.database.utils.FunctionsCoroutine.getQuerySelectByID
import com.quanticheart.data.extention.tryCatchResult
import com.quanticheart.domain.model.ToDo
import com.quanticheart.domain.model.ToDoInsert
import com.quanticheart.domain.model.ToDoSimple
import com.quanticheart.domain.repository.ToDoRepository
import com.quanticheart.domain.result.ResultRepository
import java.util.*

class ToDoRepositoryImpl(private val database: ToDoDao) : ToDoRepository {
    override suspend fun getDetails(id: String): ResultRepository<ToDo> {
        return coroutineIO {
            tryCatchResult("Erro ao tentar carregar detalhes") {
                val q = getQuerySelectByID<ToDoEntity>(id)
                database.selectByID(q).mapToDomainModel()
            }
        }
    }

    override suspend fun getList(): ResultRepository<List<ToDoSimple>> {
        return coroutineIO {
            tryCatchResult("Erro ao tentar carregar lista") {
                val q = getQuerySelectAll<ToDoEntity>()
                database.selectAll(q).map { it.mapToDomainListModel() }
            }
        }
    }

    override suspend fun delete(id: String): ResultRepository<Boolean> {
        return coroutineIO {
            tryCatchResult("Erro ao tentar deletar") {
                val q = getQueryDeleteTableByID<ToDoEntity>("id", id)
                database.rawQuerySimple(q) > 0
            }
        }
    }

    override suspend fun insert(toDo: ToDoInsert): ResultRepository<Boolean> {
        return coroutineIO {
            tryCatchResult("Erro ao tentar inserir") {
//                database.insert(
//                    ToDoEntity(
//                        date = toDo.date.time,
//                        title = toDo.title,
//                        description = toDo.description,
//                        color = toDo.color,
//                        alarm = toDo.alarm.time,
//                        check = toDo.check,
//                        type = toDo.type
//                    )
//                ) > 0

                database.insert(
                    ToDoEntity(
                        date = Date().time,
                        title = toDo.title,
                        description = "TESTES",
                        color = "#000000",
                        alarm = Date().time,
                        check = false,
                        type = 1
                    )
                ) > 0
            }
        }
    }
}
