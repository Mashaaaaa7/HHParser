package dev.mary.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json

val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
}

fun Application.configureContentNegotiation() = install(ContentNegotiation) {
    json(json)
}
