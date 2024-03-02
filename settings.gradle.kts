rootProject.name = "exchange-server"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include(":server")
include(":domain")
include(":application")
include(":infraestructure:api")
include(":infraestructure:persistence")
include(":infraestructure:remote")
include("infraestructure:events")
findProject(":infraestructure:events")?.name = "events"
