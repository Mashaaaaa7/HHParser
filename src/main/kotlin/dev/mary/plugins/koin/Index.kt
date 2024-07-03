package dev.mary.plugins.koin

import io.ktor.server.application.Application
import io.ktor.server.application.install
import dev.mary.infrastructure.repositories.RepositoriesModule
import dev.mary.infrastructure.services.ServicesModule
import org.koin.ksp.generated.module
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() = install(Koin) {
    modules(
        RepositoriesModule().module,
        ServicesModule().module
    )
}
