package com.infbyte.geneblock

import com.infbyte.geneblock.plugins.configureDatabase
import com.infbyte.geneblock.plugins.configureHTTP
import com.infbyte.geneblock.plugins.configureRouting
import com.infbyte.geneblock.plugins.configureSerialization
import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.app() {
    val dbConnection = configureDatabase()
    configureHTTP()
    configureRouting(dbConnection)
    configureSerialization()
}
