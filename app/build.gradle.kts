import com.android.build.gradle.internal.tasks.factory.dependsOn
import java.util.Properties

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
val keyStorePass: String? = properties.getProperty("key.store.pass")
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

    signingConfigs {
        create("release") {
            storeFile = file("./src/main/gene_release_keystore.jks")
            storePassword = System.getenv("KEYSTORE_PASS") ?: keyStorePass
            keyPassword = System.getenv("KEY_PASS") ?: keyPass
            keyAlias = System.getenv("KEY_ALIAS") ?: geneKeyAlias
        }
    }

    buildTypes {
        debug {
            manifestPlaceholders.putAll(
                arrayOf(
                    "appIcon" to "@mipmap/geneblock_debug",
                    "appRoundIcon" to "@mipmap/geneblock_debug_round",
                    "appName" to "@string/app_name_debug",
                ),
            )
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true

            ndk {
                debugSymbolLevel = "SYMBOL_TABLE"
            }

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )

            manifestPlaceholders.putAll(
                arrayOf(
                    "appIcon" to "@mipmap/geneblock",
                    "appRoundIcon" to "@mipmap/geneblock_round",
                    "appName" to "@string/app_name",
                ),
            )

            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.core.splashscreen)

    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    implementation(libs.koin.compose.di)
    implementation(libs.koin.compose.viewmodel)
    implementation(libs.koin.compose.viewmodel.navigation)

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
