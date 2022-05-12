package plugin

import dependencyInjectionImplementation
import extentions.java
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class CommonKotlinBaseAppPlugin : Plugin<Project> {
    override fun apply(project: Project) =
        with(project) {
            applyPlugins()
            java()
            dependenciesConfig()
        }

    private fun Project.applyPlugins() {
        plugins.run {
            apply("java-library")
            apply("org.jetbrains.kotlin.jvm")
        }

        apply {
            from("$rootDir/buildSrc/config/ktlint.gradle.kts")
        }
    }

    private fun Project.dependenciesConfig() {
        dependencies {
            dependencyInjectionImplementation()
        }
    }
}
