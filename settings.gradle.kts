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
include(":application")
include(":infraestructure:api")
include(":infraestructure:persist")
include(":infraestructure:remote")