plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {

    jvm()
    
    sourceSets {
        commonMain.dependencies {
            implementation(projects.domain)
            implementation(libs.koin.core)
            implementation(libs.logback)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test.junit)
        }
    }
}
