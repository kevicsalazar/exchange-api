plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

dependencies {
    implementation(projects.domain)
    implementation(projects.application)
    implementation(libs.koin.core)
    implementation(libs.koin.ktor)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.aws.sqs)
    implementation(libs.aws.secretsmanager)
    implementation(libs.aws.okhttp)
    implementation(libs.aws.crt)
}