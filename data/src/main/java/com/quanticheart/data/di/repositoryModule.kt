package com.quanticheart.data.di

import com.quanticheart.data.repository.ToDoRepositoryImpl
import com.quanticheart.domain.repository.ToDoRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<ToDoRepository> { ToDoRepositoryImpl(get()) }
}