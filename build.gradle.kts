plugins {

    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.serialization)

    alias(libs.plugins.detekt)
    alias(libs.plugins.ksp)

    id("application")

}

group = "dev.mary"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.h4kt.dev/releases")
}

dependencies {

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.serialization.hocon)

    detekt(libs.detekt.cli)
    detekt(libs.detekt.formatting)

    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.contentNegotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.docs)

    implementation(libs.koin.core)
    implementation(libs.koin.ktor)
    implementation(libs.koin.annotations)

    implementation(libs.exposed.core)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.kotlin.datetime)

    implementation(libs.h2)
    implementation(libs.postgres)

    ksp(libs.koin.compiler)

    testImplementation("org.jetbrains.kotlin:kotlin-test")

}

kotlin {
    jvmToolchain(21)
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
    arg("KOIN_DEFAULT_MODULE", "false")
}

detekt {
    config.setFrom("detekt.yml")
    buildUponDefaultConfig = false
}

application {
    mainClass = "dev.mary.AppKt"
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=true")
}

tasks {

    test {
        useJUnitPlatform()
    }

    getByName<JavaExec>("run") {
        workingDir = File("run")
    }

}
