package com.infbyte.shared.network

import com.infbyte.shared.logging.GLogger
import com.infbyte.shared.models.Block
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GenesisClient : Client<Block> {
    private val httpClient =
        HttpClient(CIO) {
            install(ContentNegotiation) { json() }
            install(HttpTimeout) {
                requestTimeoutMillis = 2000
            }
        }

    override fun getAll(onNetworkResult: (NetworkResult) -> Unit): Flow<List<Block>> =
        flow {
            try {
                val response = httpClient.get(ALL_BLOCKS)
                val blocks: List<Block> = response.body()
                emit(blocks)
                onNetworkResult(NetworkResult.Success)
            } catch (_: HttpRequestTimeoutException) {
                GLogger.debug(LOG_TAG, "Request time out")
                onNetworkResult(NetworkResult.Timeout)
            } catch (e: Exception) {
                GLogger.debug(LOG_TAG, "Failed to retrieve blocks with exception: $e")
                onNetworkResult(NetworkResult.ConnectivityOut)
            }
        }

    companion object {
        private const val LOG_TAG = "Genesis Client"
    }
}

sealed class NetworkResult(
    info: String,
) {
    object Success : NetworkResult("Blocks retrieved successfully")

    object Timeout : NetworkResult("Request time out. Try again")

    object ConnectivityOut : NetworkResult("Failed to retrieve blocks, server may be offline. Try again later")
}
