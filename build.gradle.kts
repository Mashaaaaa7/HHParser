plugins {

    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.serialization)

    alias(libs.plugins.ksp)

    id("application")

    id("io.ktor.plugin") version "2.3.12"

}

group = "dev.mary"
version = "1.0.0"

val exposedVersion: String by project

repositories {
    mavenCentral()
    maven("https://repo.h4kt.dev/releases")
    maven("https://jitpack.io")
}

dependencies {

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.serialization.hocon)

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
    implementation("org.jetbrains.exposed:exposed-json:$exposedVersion")

    implementation("io.ktor:ktor-client-core:2.0.0")
    implementation("io.ktor:ktor-client-cio:2.0.0")
    implementation("io.ktor:ktor-client-serialization:2.0.0")
    implementation("org.jetbrains.exposed:exposed-jodatime:0.36.2")
    implementation("com.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:6.1.0")

}

kotlin {
    jvmToolchain(21)
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
    arg("KOIN_DEFAULT_MODULE", "false")
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
