package com.quanticheart.data.database.dao

import androidx.room.Dao
import com.quanticheart.data.database.base.BaseDaoCoroutine
import com.quanticheart.data.database.model.ToDoEntity

@Dao
interface ToDoDao : BaseDaoCoroutine<ToDoEntity>