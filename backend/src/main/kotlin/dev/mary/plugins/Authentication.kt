package dev.mary.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.configureAuthentication() = authentication {

    bearer("template") {
        authenticate {
            UserIdPrincipal(it.token)
        }
    }

}
