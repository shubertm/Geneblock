import java.util.Properties

import com.android.build.gradle.internal.tasks.factory.dependsOn

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.gradle.ktlint)
}

val properties: Properties = Properties()
val propertiesFile: File = rootProject.file("local.properties")
if (propertiesFile.exists()) {
    properties.load(propertiesFile.inputStream())
}

val testBannerAdUnitId: String? = properties.getProperty("test.banner.ad.unit.id")
val bannerAdUnitId: String? = properties.getProperty("banner.ad.unit.id")
val banner1AdUnitId: String? = properties.getProperty("banner.1.ad.unit.id")
val admobAppId: String? = properties.getProperty("admob.app.id")
val geneKeyAlias: String? = properties.getProperty("key.alias")
val signingKeyStorePass: String? = properties.getProperty("key.store.pass")
val keyPass: String? = properties.getProperty("key.pass")
val localVersion: String? = properties.getProperty("local.version")
val geneVersionCode: Int =
    properties.getProperty("local.version.code")?.toInt()
        ?: System.getenv("RELEASES")?.toInt() ?: 0

android {
    namespace = "com.infbyte.geneblock"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.infbyte.geneblock"
        minSdk = 24
        targetSdk = 35
        versionCode = geneVersionCode + 1
        versionName = System.getenv("VERSION_NAME") ?: localVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.core.splashscreen)

    implementation(libs.androidx.room.runtime)

    ksp(libs.androidx.room.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

tasks.preBuild.dependsOn("ktlintCheck")
tasks.ktlintCheck.dependsOn("ktlintFormat")
