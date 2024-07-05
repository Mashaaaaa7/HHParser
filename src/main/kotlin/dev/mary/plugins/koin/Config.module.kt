package dev.mary.plugins.koin

import dev.mary.config.database.DatabaseConfig
import dev.mary.config.database.DefaultDatabaseConfig
import org.koin.dsl.module

val configModule = module(createdAtStart = true) {
    single<DatabaseConfig> { DefaultDatabaseConfig.load() }
}
