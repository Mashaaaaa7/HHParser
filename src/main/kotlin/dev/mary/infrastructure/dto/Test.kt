package dev.mary.infrastructure.dto

import kotlinx.serialization.Serializable
import dev.mary.compat.SerialUUID

@Serializable
data class Test(
    val id: SerialUUID? = null,
    val value: String
)
