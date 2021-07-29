package com.quanticheart.data.di

import androidx.room.Room
import com.quanticheart.data.database.ToDoDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val APP_DB = "app-database"

val databaseModule = module {
    single { Room.databaseBuilder(androidContext(), ToDoDatabase::class.java, APP_DB).build() }
    factory { get<ToDoDatabase>().toDoDao() }
}
