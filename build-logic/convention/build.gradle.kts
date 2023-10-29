import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "uk.co.stellar.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        // region Application
        register("androidApplicationCompose") {
            id = "stellar.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "stellar.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationJacoco") {
            id = "stellar.android.application.jacoco"
            implementationClass = "AndroidApplicationJacocoConventionPlugin"
        }
        // endregion Application

        // region Library
        register("androidLibrary") {
            id = "stellar.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("jvmLibrary") {
            id = "stellar.kotlin.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        // endregion Library

        // region Android Add-ons
        register("androidLibraryJacoco") {
            id = "stellar.android.with.jacoco"
            implementationClass = "AndroidJacocoConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "stellar.android.with.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("androidHilt") {
            id = "stellar.android.with.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidRoom") {
            id = "stellar.android.with.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("androidLifecycle") {
            id = "stellar.android.with.lifecycle"
            implementationClass = "AndroidWithLifecycleConventionPlugin"
        }
        // endregion Android Add-on

        // region Any Add-Ons
        register("androidLint") {
            id = "stellar.with.lint"
            implementationClass = "AndroidLintConventionPlugin"
        }
        // endregion Any Add-Ons
    }
}