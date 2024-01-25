plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {

    jvm()
    
    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.kotlinx.serialization.json)
            implementation("io.jsonwebtoken:jjwt-api:0.10.5")
        }
    }
}
