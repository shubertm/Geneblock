package com.infbyte.shared.network

import kotlinx.coroutines.flow.Flow

interface Client<T> {
    fun getAll(): Flow<List<T>>
}
