
plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {

    jvm()
    
    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
        }
    }
}
