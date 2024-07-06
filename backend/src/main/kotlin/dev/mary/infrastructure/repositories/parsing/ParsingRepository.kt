package dev.mary.infrastructure.repositories.parsing

import dev.mary.infrastructure.dto.Parsing

interface ParsingRepository {

    fun create(dto: Parsing): Parsing

    fun findLast(): Parsing?

    fun update(
        id: Int,
        dto: Parsing
    )

    fun delete(id: Int)

}
