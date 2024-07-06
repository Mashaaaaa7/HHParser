package dev.mary.config

import io.ktor.server.config.*
import io.ktor.server.config.ConfigLoader.Companion.load

object TelegramConfig {

    private val handle = ConfigLoader.load("telegram.conf")

    val token = handle.property("token").getString()

}