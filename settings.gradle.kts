pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Stellar"

/**
 * Allows projects to be called in dependencies like so:
 * ```
 * dependencies {
 *   // type-safe alternative to project(":client")
 *   implementation projects.client
 * }
 * ```
 */
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")

include(":asteroids:data")
include(":asteroids:domain")
include(":core-ktx")
include(":feature:asteroids")
include(":asteroids:wiring")
include(":test:presentation")
