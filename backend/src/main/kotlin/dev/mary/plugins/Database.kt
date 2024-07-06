package dev.mary.plugins

import dev.mary.config.database.DatabaseConfig
import dev.mary.infrastructure.models.ParsingModel
import dev.mary.infrastructure.models.VacancyModel
import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.ktor.ext.inject

fun Application.configureDatabase() {

    val config by inject<DatabaseConfig>()

    Database.connect(
        url = config.jdbcConnectionUrl,
        user = config.username,
        password = config.password
    )

    transaction {
        SchemaUtils.createMissingTablesAndColumns(
            ParsingModel,
            VacancyModel
        )
    }

}

suspend fun <R> suspendedTransaction(statement: Transaction.() -> R) = newSuspendedTransaction(
    context = Dispatchers.IO,
    statement = statement
)
