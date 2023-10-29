plugins {
    alias(libs.plugins.stellar.android.library)
    alias(libs.plugins.stellar.android.with.hilt)
    alias(libs.plugins.stellar.android.with.compose)
    alias(libs.plugins.stellar.android.with.jacoco)
    alias(libs.plugins.stellar.android.with.lifecycle)
    alias(libs.plugins.stellar.with.lint)

}

android {
    namespace = "uk.co.stellar.asteroids"
}

dependencies {
    implementation(projects.asteroids.domain)
    implementation(projects.coreKtx)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.google.android.material)

    testImplementation(libs.junit4)
    testImplementation(libs.androidx.arch.core.testing)
    testImplementation(libs.mockk)
    testImplementation(libs.google.truth)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.appmattus.fixture)
    testImplementation(projects.test.presentation)

    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}