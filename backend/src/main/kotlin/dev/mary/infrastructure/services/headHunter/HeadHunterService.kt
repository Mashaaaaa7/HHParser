package dev.mary.infrastructure.services.headHunter

import dev.mary.infrastructure.dto.Vacancy

interface HeadHunterService {

    fun saveVacancies(vacancies: List<Vacancy>)

    fun getVacancies(
        offset: Long,
        limit: Int,
        query: String,
        area: String,
        salary: Int,
        orderBy: String
    ): List<Vacancy>

    fun newParse()

    fun getParsingState(): Boolean

}