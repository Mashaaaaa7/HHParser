package dev.mary.plugins

import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import dev.mary.infrastructure.controllers.configureTestRouting

fun Application.configureRouting() = routing {
    configureTestRouting()
}
