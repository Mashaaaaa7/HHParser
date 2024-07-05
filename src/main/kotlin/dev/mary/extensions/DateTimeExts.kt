package net.pixefy.main.extensions

import dev.mraksimus.extensions.now
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import org.jetbrains.exposed.sql.Column


fun Column<LocalDateTime>.defaultNow(timeZone: TimeZone) = apply {
    defaultValueFun = { LocalDateTime.now(timeZone) }
}

fun Column<LocalDateTime>.defaultNowUTC() = defaultNow(TimeZone.UTC)
