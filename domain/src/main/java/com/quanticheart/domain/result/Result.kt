package com.quanticheart.domain.result

class RepositoryError(val throwable: Throwable, val errorCode: Int = 0)

sealed class ResultRepository<out T : Any>

data class Success<out T : Any>(val data: T) : ResultRepository<T>()
data class Failure(val error: RepositoryError) : ResultRepository<Nothing>()

inline fun <T : Any> ResultRepository<T>.onSuccess(action: (T) -> Unit): ResultRepository<T> {
    if (this is Success) action(data)
    return this
}

inline fun <T : Any> ResultRepository<T>.onFailure(action: (RepositoryError) -> Unit) {
    if (this is Failure) action(error)
}
