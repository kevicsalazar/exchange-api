plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(projects.domain)
    implementation(libs.koin.core)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.kotest.engine)
    testImplementation(libs.kotest.assertions)
    testImplementation(libs.kotest.property)
    testImplementation(libs.koin.test)
    testImplementation(libs.mockative)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.kotest.runner.junit5)

    configurations
        .filter { it.name.startsWith("ksp") && it.name.contains("Test") }
        .forEach {
            add(it.name, libs.mockative.processor)
        }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}