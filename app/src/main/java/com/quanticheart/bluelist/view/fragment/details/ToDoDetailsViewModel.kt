package com.quanticheart.bluelist.view.fragment.details

import androidx.lifecycle.MutableLiveData
import com.quanticheart.core.base.viewModel.BaseViewModel
import com.quanticheart.core.generics.liveData.BooleanLiveData
import com.quanticheart.core.generics.liveData.setTrueClean
import com.quanticheart.domain.interaction.toDo.GetToDoUserCase
import com.quanticheart.domain.model.ToDo
import com.quanticheart.domain.result.onFailure
import com.quanticheart.domain.result.onSuccess

class ToDoDetailsViewModel(private val userCase: GetToDoUserCase) : BaseViewModel() {

    val details = MutableLiveData<ToDo>()
    val finishTodo = BooleanLiveData()

    fun loadDetails(id: Int) {
        coroutineScopeLaunchLoading {
//            details.value = null
            userCase.getDetails(id).onSuccess {
                details.postValue(it)
            }.onFailure {
                it.throwable.alertError()
            }
        }
    }

    fun finishToDo(id: Int) {
        coroutineScopeLaunchLoading {
            userCase.finish(id).onSuccess {
                loadDetails(id)
                finishTodo.setTrueClean()
            }.onFailure {
                it.throwable.alertError()
            }
        }
    }
}
