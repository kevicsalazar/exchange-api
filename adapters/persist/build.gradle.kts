
plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {

    jvm()
    
    sourceSets {
        commonMain.dependencies {
            implementation(projects.domain)
            implementation(libs.logback)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test.junit)
        }
    }
}
