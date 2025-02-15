package dev.mraksimus.extensions

import kotlinx.datetime.*
import kotlin.time.Duration

fun LocalDateTime.Companion.now(
    timeZone: TimeZone = TimeZone.currentSystemDefault()
) = Clock.System.now().toLocalDateTime(timeZone)

fun LocalDateTime.Companion.nowUTC() = now(TimeZone.UTC)

operator fun LocalDateTime.plus(
    duration: Duration
) = toInstant(TimeZone.UTC)
    .plus(duration)
    .toLocalDateTime(TimeZone.UTC)

operator fun LocalDateTime.minus(
    duration: Duration
) = toInstant(TimeZone.UTC)
    .minus(duration)
    .toLocalDateTime(TimeZone.UTC)

operator fun LocalDateTime.minus(
    other: LocalDateTime
) = toInstant(TimeZone.UTC)
    .minus(other.toInstant(TimeZone.UTC))
