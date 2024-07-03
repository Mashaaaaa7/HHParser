package dev.mary.plugins.koin

import dev.mary.infrastructure.repositories.test.TestRepository
import dev.mary.infrastructure.repositories.test.ExposedTestRepository
import org.koin.dsl.module

val testModule = module {
    single<TestRepository> { ExposedTestRepository() }
}
