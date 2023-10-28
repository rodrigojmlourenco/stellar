import org.gradle.api.Plugin
import org.gradle.api.Project
import uk.co.stellar.extensions.configureKotlinJvm

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
                apply("stellar.with.lint")
            }
            configureKotlinJvm()
        }
    }
}