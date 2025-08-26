package com.infbyte.geneblock

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.netty.EngineMain
import io.ktor.server.plugins.calllogging.CallLogging

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.app() {

}
