package com.infbyte.geneblock

import com.infbyte.geneblock.plugins.configureDatabase
import com.infbyte.geneblock.plugins.configureHTTP
import com.infbyte.geneblock.plugins.configureRouting
import com.infbyte.geneblock.plugins.configureSerialization
import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain

const val DEV_CONFIG_ARG = "-config=local_application.conf"

fun main(args: Array<String>) {
    val newArgs = args.toMutableSet()
    val env = System.getenv("ENV")
    if (env == "development") {
        newArgs.add(DEV_CONFIG_ARG)
    }
    EngineMain.main(newArgs.toTypedArray())
}

fun Application.module() {
    val dbConnection = configureDatabase()
    configureHTTP()
    configureRouting(dbConnection)
    configureSerialization()
}
