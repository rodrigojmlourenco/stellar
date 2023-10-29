plugins {
    alias(libs.plugins.stellar.android.application)
    alias(libs.plugins.stellar.android.application.jacoco)
    alias(libs.plugins.stellar.android.with.hilt)
    id("jacoco")
}

android {
    namespace = "uk.co.stellar"

    defaultConfig {
        applicationId = "uk.co.stellar.stargazer"
        versionCode = 1
        versionName = "1.0.0"
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
    implementation(projects.data)
    implementation(projects.data)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.android.material)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.retrofit.core)
    implementation(libs.okhttp.logging)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)
}
