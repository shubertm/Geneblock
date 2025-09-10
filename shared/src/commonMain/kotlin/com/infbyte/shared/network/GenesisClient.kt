package com.infbyte.shared.network

import com.infbyte.shared.models.Block
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GenesisClient : Client<Block> {
    private val httpClient =
        HttpClient {
            install(ContentNegotiation)
            install(HttpTimeout) {
                requestTimeoutMillis = 2000
            }
        }

    override fun getAll(): Flow<List<Block>> =
        flow {
            val response = httpClient.get(ALL_BLOCKS)
            val blocks: List<Block> = response.body()
            emit(blocks)
        }
}
