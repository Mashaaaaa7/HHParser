package dev.mary

import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain
import dev.mary.plugins.configureAuthentication
import dev.mary.plugins.configureContentNegotiation
import dev.mary.plugins.configureDatabase
import dev.mary.plugins.configureDocs
import dev.mary.plugins.configureRouting

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {

    configureDatabase()

    configureContentNegotiation()

    configureAuthentication()
    configureRouting()
    configureDocs()

}
