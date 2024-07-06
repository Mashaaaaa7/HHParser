package dev.mary.infrastructure.models

import dev.mary.infrastructure.dto.Vacancy
import dev.mary.plugins.json
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.json.jsonb
import org.jetbrains.exposed.sql.kotlin.datetime.timestampWithTimeZone

object VacancyModel : IntIdTable("vacancy") {

    val name = varchar("name", 255)
    val area = jsonb<Vacancy.Area>("area", json)
    val hasTest = bool("has_test")
    val publishedAt = timestampWithTimeZone("published_at")
    val createdAt = timestampWithTimeZone("created_at")
    val snippet = jsonb<Vacancy.Snippet>("snippet", json)
    val schedule = jsonb<Vacancy.Schedule>("schedule", json)
    val salary = jsonb<Vacancy.Salary>("salary", json)
    val address = jsonb<Vacancy.Address>("address", json)
    val contacts = jsonb<Vacancy.Contacts>("contacts", json)
    val alternateUrl = varchar("alternate_url", 255)
    val applyAlternateUrl = varchar("apply_alternate_url", 255)

}
