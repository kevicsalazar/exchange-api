plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

dependencies {
    implementation(projects.domain)
    implementation(libs.koin.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.postgresql.jdbc)
    implementation(libs.hikari)
    implementation(libs.logback)
    testImplementation(libs.kotlin.test.junit)
}
