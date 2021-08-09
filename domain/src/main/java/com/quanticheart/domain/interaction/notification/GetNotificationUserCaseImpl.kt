package com.quanticheart.domain.interaction.notification

import com.quanticheart.domain.model.ToDo
import com.quanticheart.domain.repository.NotificationRepository
import com.quanticheart.domain.result.ResultRepository

class GetNotificationUserCaseImpl(private val repository: NotificationRepository) :
    GetNotificationUserCase {
    override suspend fun getNotificationList(): ResultRepository<List<ToDo>> =
        repository.getNotificationList()
}