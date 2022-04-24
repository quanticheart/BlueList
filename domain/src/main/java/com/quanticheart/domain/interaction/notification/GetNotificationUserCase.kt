package com.quanticheart.domain.interaction.notification

import com.quanticheart.domain.model.ToDo
import com.quanticheart.domain.result.ResultRepository

interface GetNotificationUserCase {
    suspend fun getNotificationList(): ResultRepository<List<ToDo>>
}
