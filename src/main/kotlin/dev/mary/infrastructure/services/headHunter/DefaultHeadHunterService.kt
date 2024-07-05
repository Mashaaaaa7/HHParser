package dev.mary.infrastructure.services.headHunter

import dev.mary.infrastructure.dto.Parsing
import dev.mary.infrastructure.dto.Vacancy
import dev.mary.infrastructure.repositories.parsing.ParsingRepository
import dev.mary.infrastructure.repositories.vacancy.VacancyRepository
import dev.mraksimus.extensions.now
import dev.mraksimus.extensions.plus
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.time.Duration.Companion.hours

class DefaultHeadHunterService : HeadHunterService, KoinComponent {

    private val vacanciesRepository by inject<VacancyRepository>()
    private val parsingRepository by inject<ParsingRepository>()

    override fun saveVacancies(
        vacancies: List<Vacancy>
    ): Unit = transaction {

        for (vacancy in vacancies) {
            vacanciesRepository.create(vacancy)
        }

    }

    override fun getVacancies(
        offset: Long,
        limit: Int,
        query: String,
        area: String,
        salary: Int,
        orderBy: String
    ): List<Vacancy> = transaction {
        return@transaction vacanciesRepository.findAll(
            offset = offset,
            limit = limit,
            query = query,
            area = area,
            salary = salary,
            orderBy = orderBy
        ).items
    }

    override fun newParse(): Unit = transaction {

        vacanciesRepository.deleteAll()

        parsingRepository.create(
            Parsing(
                parsedAt = LocalDateTime.now()
            )
        )

    }

    override fun getParsingState(): Boolean = transaction {

        val lastParse = parsingRepository.findLast()

        val result = lastParse == null || lastParse.parsedAt + 1.hours <= LocalDateTime.now()

        return@transaction result
    }

}