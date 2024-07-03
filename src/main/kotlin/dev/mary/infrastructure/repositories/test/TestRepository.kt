package dev.mary.infrastructure.repositories.test

import dev.mary.infrastructure.dto.PageResponse
import dev.mary.infrastructure.dto.Test
import java.util.*

interface TestRepository {

    fun create(dto: Test): Test

    fun findAll(
        offset: Long,
        limit: Int
    ): PageResponse<Test>

    fun update(
        id: UUID,
        dto: Test
    )

    fun delete(id: UUID)

}
