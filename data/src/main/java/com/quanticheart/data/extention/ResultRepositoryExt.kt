package com.quanticheart.data.extention

import com.quanticheart.domain.result.Failure
import com.quanticheart.domain.result.RepositoryError
import com.quanticheart.domain.result.ResultRepository
import com.quanticheart.domain.result.Success

fun <T : Any> T.toSuccessResult(): ResultRepository<T> {
    return Success(this)
}

fun <T : Any> RepositoryError.toErrorResult(): ResultRepository<T> {
    return Failure(this)
}
