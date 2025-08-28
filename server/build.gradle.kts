plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.tests)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.metrics)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.logback)
    implementation(libs.ktor.json.serialization)
    implementation(libs.ktor.host.common)
    implementation(libs.ktor.cors)
    implementation(libs.ktor.content.negotiation)
    implementation(libs.ktor.compression)

    implementation(libs.postgresql)
    implementation(libs.h2database)

    implementation(libs.ktor.client.content.negotiation)
}
