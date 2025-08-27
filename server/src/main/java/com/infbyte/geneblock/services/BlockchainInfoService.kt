package com.infbyte.geneblock.services

import com.infbyte.shared.models.BitcoinRawBlock
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class BlockchainInfoService {

    private val ktorHttpClient = HttpClient {
        install(ContentNegotiation)
        install(HttpTimeout) { requestTimeoutMillis = 5000 }
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            init()
        }
    }

    private suspend fun init() {
        val response = ktorHttpClient.get("https://blockchain.info/rawblock/$BTC_GENESIS_HASH") {
            contentType(ContentType.Application.Json)
        }
        val body = response.bodyAsText()
        val bitcoinRawBlock = Json.decodeFromString<BitcoinRawBlock>(body)
    }

    private companion object {
        const val BTC_GENESIS_HASH = "000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f"
    }
}