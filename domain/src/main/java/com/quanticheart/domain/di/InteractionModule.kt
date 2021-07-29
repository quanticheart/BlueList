package com.quanticheart.domain.di

import com.quanticheart.domain.interaction.toDo.GetToDoUserCase
import com.quanticheart.domain.interaction.toDo.GetToDoUserCaseImpl
import org.koin.dsl.module

val interactionModule = module {
    factory<GetToDoUserCase> { GetToDoUserCaseImpl(get()) }
}