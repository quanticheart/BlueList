package com.quanticheart.bluelist.view.fragment.list

import com.quanticheart.core.base.viewModel.BaseViewModel
import com.quanticheart.core.generics.liveData.ListLiveData
import com.quanticheart.domain.interaction.toDo.GetToDoUserCase
import com.quanticheart.domain.model.ToDoInsert
import com.quanticheart.domain.model.ToDoSimple
import com.quanticheart.domain.result.onFailure
import com.quanticheart.domain.result.onSuccess

class ToDoListViewModel(private val userCase: GetToDoUserCase) : BaseViewModel() {

    val list = ListLiveData<ToDoSimple>()

    init {
        loadToDoList()
    }

    fun loadToDoList() {
        coroutineScopeLaunchLoading {
            userCase.getList().onSuccess {
                list.value = it
            }.onFailure {
                it.throwable.alertError()
            }
        }
    }

    fun addToDo(toDo: ToDoInsert) {
        coroutineScopeLaunchLoading {
            userCase.insert(toDo)
        }
    }

    fun finishToDo(id: Int) {
        coroutineScopeLaunchLoading {
            userCase.finish(id).onSuccess {
                loadToDoList()
            }.onFailure {
                it.throwable.alertError()
            }
        }
    }
}
