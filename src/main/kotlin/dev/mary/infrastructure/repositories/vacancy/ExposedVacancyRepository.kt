package dev.mary.infrastructure.repositories.vacancy

import dev.mary.extensions.deleteById
import dev.mary.extensions.updateById
import dev.mary.infrastructure.dto.PageResponse
import dev.mary.infrastructure.dto.Vacancy
import dev.mary.infrastructure.models.VacancyModel
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greaterEq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.json.extract
import org.koin.core.annotation.Single

@Single
class ExposedVacancyRepository : VacancyRepository {

    override fun create(dto: Vacancy): Vacancy {

        val insertedRow = VacancyModel.insert {
            it[name] = dto.name
            it[area] = dto.area!!
            it[hasTest] = dto.hasTest!!
            it[publishedAt] = dto.publishedAt!!
            it[createdAt] = dto.createdAt!!
            it[snippet] = dto.snippet
            it[schedule] = dto.schedule
            it[salary] = dto.salary
            it[address] = dto.address
            it[contacts] = dto.contacts
            it[alternateUrl] = dto.alternateUrl!!
            it[applyAlternateUrl] = dto.applyAlternateUrl!!
        }

        return dto.copy(id = insertedRow[VacancyModel.id].value)
    }

    override fun deleteAll() {
        VacancyModel.deleteAll()
    }

    override fun findAll(
        offset: Long,
        limit: Int,
        query: String,
        area: String,
        salary: Int,
        orderBy: String
    ): PageResponse<Vacancy> {

        val sortOrder = if (orderBy == "ASC") SortOrder.ASC else SortOrder.DESC

        val total = VacancyModel.selectAll().count()
        val items = VacancyModel.selectAll()
            .where(
                (VacancyModel.name like "%$query%")
                    .and(VacancyModel.salary.extract<Int>("from").castTo(IntegerColumnType()) greaterEq salary)
                    .and(VacancyModel.area.extract<String>("name") like "%$area%")
            )
            .orderBy(VacancyModel.name to sortOrder)
            .limit(limit, offset)
            .map { it.toVacancy() }

        return PageResponse(
            total = total,
            offset = offset,
            limit = limit,
            items = items
        )
    }

    override fun update(
        id: Int,
        dto: Vacancy
    ) {

        VacancyModel.updateById(id) {
            it[name] = dto.name
            it[area] = dto.area!!
            it[hasTest] = dto.hasTest!!
            it[publishedAt] = dto.publishedAt!!
            it[createdAt] = dto.createdAt!!
            it[snippet] = dto.snippet
            it[schedule] = dto.schedule
            it[salary] = dto.salary
            it[address] = dto.address
            it[contacts] = dto.contacts
            it[alternateUrl] = dto.alternateUrl!!
            it[applyAlternateUrl] = dto.applyAlternateUrl!!
        }

    }

    override fun delete(id: Int) {
        VacancyModel.deleteById(id)
    }

    private fun ResultRow.toVacancy() = Vacancy(
        id = this[VacancyModel.id].value,
        name = this[VacancyModel.name],
        area = this[VacancyModel.area],
        hasTest = this[VacancyModel.hasTest],
        publishedAt = this[VacancyModel.publishedAt],
        createdAt = this[VacancyModel.createdAt],
        snippet = this[VacancyModel.snippet],
        schedule = this[VacancyModel.schedule],
        salary = this[VacancyModel.salary],
        address = this[VacancyModel.address],
        contacts = this[VacancyModel.contacts],
        alternateUrl = this[VacancyModel.alternateUrl],
        applyAlternateUrl = this[VacancyModel.applyAlternateUrl],
    )

}
