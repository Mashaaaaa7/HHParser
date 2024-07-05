package dev.mary

import dev.mary.plugins.*
import dev.mary.plugins.koin.configureKoin
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {

    configureKoin()

    configureDatabase()

    configureContentNegotiation()

    configureAuthentication()
    configureRouting()
    configureDocs()

}
