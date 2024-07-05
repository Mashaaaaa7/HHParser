package dev.mary.infrastructure.models

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object ParsingModel : IntIdTable("parsing") {
    val parsedAt = datetime("parsed_at")
}
