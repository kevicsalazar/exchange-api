rootProject.name = "exchange-server"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include(":server")
include(":domain")
include(":adapters:api")
include(":adapters:persist")
include(":adapters:remote")