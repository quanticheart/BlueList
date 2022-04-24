package com.quanticheart.data.repository

import com.quanticheart.core.extentions.system.coroutineIO
import com.quanticheart.core.extentions.vars.toDate
import com.quanticheart.data.database.dao.ToDoDao
import com.quanticheart.data.database.model.ToDoEntity
import com.quanticheart.data.database.utils.FunctionsCoroutine.getQuerySelectAll
import com.quanticheart.data.database.utils.Where
import com.quanticheart.data.extention.tryCatchResult
import com.quanticheart.domain.model.ToDo
import com.quanticheart.domain.repository.NotificationRepository
import com.quanticheart.domain.result.ResultRepository
import java.text.SimpleDateFormat
import java.util.*

class NotificationRepositoryImpl(private val database: ToDoDao) : NotificationRepository {
    override suspend fun getNotificationList(): ResultRepository<List<ToDo>> {
        return coroutineIO {
            tryCatchResult("Erro ao tentar carregar lista") {
                val now = Date()

                val millis: Long = 60000
                val date = Calendar.getInstance()
                val t = date.timeInMillis
                val future = Date(t + 1 * millis)

                val date1 =
                    SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(now)
                        .toDate("yyyy-MM-dd HH:mm")

                val date2 =
                    SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(future)
                        .toDate("yyyy-MM-dd HH:mm")

                val q = getQuerySelectAll<ToDoEntity>(
                    Where("alarm", ">=", date1?.time.toString()),
                    Where("alarm", "<=", date2?.time.toString())
                )
                database.selectAll(q).map { it.mapToDomainModel() }
                    .sortedByDescending { it.date }
            }
        }
    }
}
