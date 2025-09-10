package com.infbyte.geneblock.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.compression.Compression
import io.ktor.server.plugins.cors.routing.CORS

fun Application.configureHTTP() {
    install(CORS)
    install(Compression)
}
