plugins {
    alias(libs.plugins.stellar.kotlin.library)
    alias(libs.plugins.stellar.with.lint)
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
}
