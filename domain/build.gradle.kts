plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.kotestMultiplatform)
    alias(libs.plugins.ksp)
}

kotlin {

    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.kotlinx.serialization.json)
        }
        commonTest.dependencies {
            implementation(libs.kotest.engine)
            implementation(libs.kotest.assertions)
            implementation(libs.kotest.property)
            implementation(libs.koin.test)
            implementation(libs.mockative)
        }
        jvmTest.dependencies {
            implementation(libs.kotlin.test.junit)
            implementation(libs.kotest.runner.junit5)
        }
    }
}

tasks.named<Test>("jvmTest") {
    useJUnitPlatform()
    filter {
        isFailOnNoMatchingTests = false
    }
    testLogging {
        showExceptions = true
        showStandardStreams = true
        events = setOf(
            org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
        )
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}

dependencies {
    configurations
        .filter { it.name.startsWith("ksp") && it.name.contains("Test") }
        .forEach {
            add(it.name, libs.mockative.processor)
        }
}