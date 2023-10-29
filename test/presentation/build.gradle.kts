plugins {
    alias(libs.plugins.stellar.android.library)
    alias(libs.plugins.stellar.android.with.lifecycle)
}

android.namespace = "uk.co.stellar.test.presentation"

dependencies {
    implementation(libs.junit4)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.test)
}