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
    implementation(projects.adapters.api)
    implementation(projects.adapters.persist)
    implementation(projects.adapters.remote)
    implementation(projects.domain)
    implementation(libs.koin.logger)
    implementation(libs.koin.core)
    implementation(libs.koin.ktor)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}