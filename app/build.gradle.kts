import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.stellar.android.application)
    alias(libs.plugins.stellar.android.application.jacoco)
    alias(libs.plugins.stellar.android.application.compose)
    alias(libs.plugins.stellar.android.with.hilt)
    id("jacoco")
}

android {
    namespace = "uk.co.stellar"

    defaultConfig {
        applicationId = "uk.co.stellar.stargazer"
        versionCode = 1
        versionName = "1.0.0"

        val key: String = gradleLocalProperties(rootDir).getProperty("nasa_key")
        buildConfigField("String", "NASA_KEY", "\"$key\"")
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }
        val release by getting {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(projects.coreKtx)
    implementation(projects.asteroids.wiring)
    implementation(projects.asteroids.data)
    implementation(projects.asteroids.domain)
    implementation(projects.feature.asteroids)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.android.material)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.retrofit.core)
    implementation(libs.okhttp.logging)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.fragment)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)
}
