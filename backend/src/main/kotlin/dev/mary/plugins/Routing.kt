package dev.mary.plugins

import dev.mary.infrastructure.controllers.configureTestRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() = routing {
    configureTestRouting()
}
