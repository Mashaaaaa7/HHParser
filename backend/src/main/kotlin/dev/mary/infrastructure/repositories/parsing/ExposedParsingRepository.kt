package dev.mary.infrastructure.repositories.parsing

import dev.mary.extensions.deleteById
import dev.mary.extensions.updateById
import dev.mary.infrastructure.dto.Parsing
import dev.mary.infrastructure.models.ParsingModel
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.koin.core.annotation.Single

@Single
class ExposedParsingRepository : ParsingRepository {

    override fun create(dto: Parsing): Parsing {

        val insertedRow = ParsingModel.insert {
            it[parsedAt] = dto.parsedAt
        }

        return dto.copy(id = insertedRow[ParsingModel.id].value)
    }

    override fun findLast(): Parsing? {
        return ParsingModel
            .selectAll()
            .orderBy(ParsingModel.parsedAt to SortOrder.DESC)
            .firstOrNull()
            ?.toParsingDto()
    }

    override fun update(
        id: Int,
        dto: Parsing
    ) {
        ParsingModel.updateById(id) {
            it[parsedAt] = dto.parsedAt
        }
    }

    override fun delete(id: Int) {
        ParsingModel.deleteById(id)
    }

    private fun ResultRow.toParsingDto() = Parsing(
        id = this[ParsingModel.id].value,
        parsedAt = this[ParsingModel.parsedAt]
    )

}
