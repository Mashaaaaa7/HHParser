package dev.mary.plugins.koin

import dev.mary.config.ConfigModule
import dev.mary.infrastructure.repositories.RepositoriesModule
import dev.mary.infrastructure.services.ServicesModule
import io.ktor.server.application.*
import org.koin.ksp.generated.module
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() = install(Koin) {
    modules(
        ConfigModule().module,
        RepositoriesModule().module,
        ServicesModule().module,
        configModule,
        headHunterModule
    )
}
