package dev.mary.infrastructure.dto

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Parsing(
    val id: Int? = null,
    val parsedAt: LocalDateTime,
)
