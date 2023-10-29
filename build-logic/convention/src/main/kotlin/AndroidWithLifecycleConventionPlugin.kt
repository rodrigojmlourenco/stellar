import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import uk.co.stellar.extensions.libs

class AndroidWithLifecycleConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {

            dependencies {
                add("implementation", libs.findLibrary("androidx.activity.ktx").get())
                add("implementation", libs.findLibrary("androidx.fragment.ktx").get())

                add("implementation", libs.findLibrary("androidx.lifecycle.livedata.ktx").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModel.ktx").get())
            }
        }
    }
}
