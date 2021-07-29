package com.quanticheart.data.extention
/* ktlint-disable no-wildcard-imports */
import kotlinx.coroutines.*

/**
 * Dispatchers.IO - Este dispatcher é otimizado para executar E / S de disco ou rede fora do encadeamento principal.
 * Os exemplos incluem o uso do componente Room , leitura ou gravação de arquivos e execução de quaisquer operações de rede.
 */
suspend fun <T, E> T.coroutineIO(block: suspend (T) -> E): E {
    return withContext(Dispatchers.IO) {
        block(this@coroutineIO)
    }
}

