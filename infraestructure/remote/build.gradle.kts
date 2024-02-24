plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {

    jvm()
    
    sourceSets {
        commonMain.dependencies {
            implementation(projects.domain)
            implementation(libs.bundles.ktor.client)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.koin.core)
            implementation(libs.logback)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test.junit)
        }
        jvmMain.dependencies {
            implementation(libs.ktor.client.apache5)
        }
    }
}
