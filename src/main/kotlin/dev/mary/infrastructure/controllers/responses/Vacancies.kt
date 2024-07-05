package dev.mary.infrastructure.controllers.responses

import dev.mary.infrastructure.dto.Vacancy
import kotlinx.serialization.Serializable

@Serializable
data class Vacancies(
    val items: List<Vacancy>,
    val pages: Int,
    val page: Int
)
