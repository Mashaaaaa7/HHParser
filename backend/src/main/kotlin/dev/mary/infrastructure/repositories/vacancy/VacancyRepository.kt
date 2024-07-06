package dev.mary.infrastructure.repositories.vacancy

import dev.mary.infrastructure.dto.PageResponse
import dev.mary.infrastructure.dto.Vacancy

interface VacancyRepository {

    fun create(dto: Vacancy): Vacancy

    fun deleteAll()

    fun findAll(
        offset: Long,
        limit: Int,
        query: String,
        area: String,
        salary: Int,
        orderBy: String
    ): PageResponse<Vacancy>

    fun update(
        id: Int,
        dto: Vacancy
    )

    fun delete(id: Int)

}
