package dev.mary.plugins.koin

import dev.mary.infrastructure.services.headHunter.DefaultHeadHunterService
import dev.mary.infrastructure.services.headHunter.HeadHunterService
import org.koin.dsl.module

val headHunterModule = module(createdAtStart = true) {
    single<HeadHunterService> { DefaultHeadHunterService() }
}