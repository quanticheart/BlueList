package com.quanticheart.bluelist.view.fragment.details

import androidx.lifecycle.MutableLiveData
import com.quanticheart.core.base.viewModel.BaseViewModel
import com.quanticheart.domain.interaction.toDo.GetToDoUserCase
import com.quanticheart.domain.model.ToDo
import com.quanticheart.domain.result.onFailure
import com.quanticheart.domain.result.onSuccess

class ToDoDetailsViewModel(private val userCase: GetToDoUserCase) : BaseViewModel() {

    val details = MutableLiveData<ToDo>()
    val finishTodo = MutableLiveData<Boolean>()

    fun loadDetails(id: Int) {
        coroutineScopeLaunchLoading {
            details.value = null
            userCase.getDetails(id).onSuccess {
                details.value = it
            }.onFailure {
                it.throwable.alertError()
            }
        }
    }

    fun finishToDo(id: Int) {
        coroutineScopeLaunchLoading {
            userCase.finish(id).onSuccess {
                loadDetails(id)
                finishTodo.value = true
            }.onFailure {
                it.throwable.alertError()
            }
        }
    }
}
