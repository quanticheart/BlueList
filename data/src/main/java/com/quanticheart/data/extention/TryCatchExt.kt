package com.quanticheart.data.extention

import com.quanticheart.core.exeptions.sendException
import com.quanticheart.core.extentions.toThrowable
import com.quanticheart.domain.result.RepositoryError
import com.quanticheart.domain.result.ResultRepository

inline fun <T : Any> tryCatchResult(msgError: String, block: () -> T): ResultRepository<T> {
    return try {
        block().toSuccessResult()
    } catch (e: Exception) {
        e.sendException()
        RepositoryError(msgError.toThrowable()).toErrorResult()
    }
}
