package com.quanticheart.data.mapper

interface DomainMapperWithList<T : Any, L : Any> {
    fun mapToDomainModel(): T
    fun mapToDomainListModel(): L
}
