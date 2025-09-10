package com.infbyte.geneblock.routes

import com.infbyte.geneblock.services.Service
import com.infbyte.shared.models.Block
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.genesisRouting(service: Service<Block>) {
    get("/genesis/blocks") {
        service.getAll().collect { blocks ->
            call.respond(HttpStatusCode.OK, blocks)
        }
    }
}
