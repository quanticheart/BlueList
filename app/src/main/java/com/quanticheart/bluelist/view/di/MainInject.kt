package com.quanticheart.bluelist.view.di

import com.quanticheart.bluelist.view.MainViewModel
import com.quanticheart.bluelist.view.fragment.details.ToDoDetailsViewModel
import com.quanticheart.bluelist.view.fragment.list.ToDoListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ToDoListViewModel(get()) }
    viewModel { ToDoDetailsViewModel(get()) }
    viewModel { MainViewModel() }
}
