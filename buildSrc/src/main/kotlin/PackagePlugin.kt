import org.gradle.api.Plugin
import org.gradle.api.Project

class PackagePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.configurations.register("packageImplementation") {
            project.configurations.named("implementation").get().extendsFrom(this)
        }
    }
}