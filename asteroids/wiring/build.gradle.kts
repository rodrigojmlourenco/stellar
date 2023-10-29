plugins {
    alias(libs.plugins.stellar.android.library)
    alias(libs.plugins.stellar.android.with.hilt)
}

android.namespace = "uk.co.asteroids.wiring"

dependencies {
    implementation(projects.asteroids.domain)
    implementation(projects.asteroids.data)
    implementation(projects.coreKtx)

    implementation(libs.retrofit.core)
}