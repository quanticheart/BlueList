package com.quanticheart.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.quanticheart.data.database.dao.ToDoDao
import com.quanticheart.data.database.model.ToDoEntity

@Database(entities = [ToDoEntity::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}
