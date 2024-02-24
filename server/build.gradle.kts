plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "dev.kevinsalazar.exchange.server"
version = "1.0.0"

application {
    mainClass.set("dev.kevinsalazar.exchange.server.AppKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["development"] ?: "false"}")
}

dependencies {
    implementation(projects.infraestructure.api)
    implementation(projects.infraestructure.persist)
    implementation(projects.infraestructure.remote)
    implementation(projects.application)
    implementation(projects.domain)
    implementation(libs.koin.logger)
    implementation(libs.koin.core)
    implementation(libs.koin.ktor)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.auth.jwt)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}