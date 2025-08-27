package com.infbyte.geneblock.services

import kotlinx.coroutines.flow.Flow

interface Service<T> {
    suspend fun init()

    suspend fun insert(item: T)

    suspend fun update(item: T)

    suspend fun delete(id: Long)

    fun get(): Flow<T>

    fun getAll(): Flow<List<T>>
}