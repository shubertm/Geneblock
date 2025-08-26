package com.infbyte.geneblock.plugins

import com.infbyte.geneblock.routes.genesisRouting
import com.infbyte.geneblock.services.GenesisService
import com.infbyte.geneblock.services.Service
import com.infbyte.shared.models.Block
import io.ktor.server.application.Application
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import java.sql.Connection

fun Application.configureRouting(dbConnection: Connection) {
    val genesisService: Service<Block> = GenesisService(dbConnection)

    routing {
        get("/") {
          call.respondText("Welcome to Geneblock, back to where the Blockchain begun")
        }

        genesisRouting(genesisService)
    }
}