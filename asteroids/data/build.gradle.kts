plugins {
    alias(libs.plugins.stellar.kotlin.library)
    alias(libs.plugins.stellar.with.lint)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(projects.asteroids.domain)
    implementation(projects.coreKtx)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.android)

    testImplementation(libs.okhttp.mockwebserver)
    testImplementation(libs.mockk)
    testImplementation(libs.appmattus.fixture)
    testImplementation(libs.turbine)
    testImplementation(libs.google.truth)
    testImplementation(libs.kotlinx.coroutines.test)
}
