import com.android.build.gradle.internal.tasks.factory.dependsOn

plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.gradle.ktlint)
}

group = "com.infbyte.geneblock"
version = "1.0.0"

application {
    mainClass.set("com.infbyte.geneblock.ApplicationKt")
}

ktor {
    fatJar {
        archiveFileName.set("geneblock.jar")
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

tasks.compileKotlin.dependsOn("ktlintCheck")
tasks.ktlintCheck.dependsOn("ktlintFormat")
