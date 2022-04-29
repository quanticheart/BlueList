package plugin

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import dependencyInjectionImplementation
import extentions.*
import firebaseCoreImplementation
import kotlinImplementation
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withGroovyBuilder
import testsImplementation
import uiImplementation
import java.io.File

@Suppress("unused")
class CommonBaseAppPlugin : Plugin<Project> {

    override fun apply(project: Project) =
        with(project) {
            applyPlugins()
            androidConfig()
            dependenciesConfig()
            tasks()
        }

    private fun Project.applyPlugins() {
        plugins.run {
            apply("kotlin-android")
            apply("kotlin-kapt")
        }
    }

    private fun Project.androidConfig() {
        android.run {
            if (!moduleIsLibrary()) {
                appModule {
                    signingConfigs {
                        getByName("debug") {
                            keyAlias = "debug"
                            keyPassword = "123456"
                            storeFile = File("$rootDir/app/key/debugkey")
                            storePassword = "123456"
                        }
                        create("release") {
                            keyAlias = "debug"
                            keyPassword = "123456"
                            storeFile = File("$rootDir/app/key/debugkey")
                            storePassword = "123456"
                        }
                    }
                    lint.baseline = File("${project.buildDir}/baseline.xml")
                }
            }
            appVersionsOnlyModule(moduleIsLibrary())
            appBuildTypes(moduleIsLibrary())

            withGroovyBuilder {
                "buildFeatures" {
                    setProperty("viewBinding", true)
                    setProperty("dataBinding", true)
                }
                "kotlinOptions" {
                    setProperty("jvmTarget", "1.8")
                }
            }

            appCompileOptions()
        }
    }

    private fun Project.dependenciesConfig() {
        dependencies {
            kotlinImplementation()
            dependencyInjectionImplementation()
            uiImplementation()
            firebaseCoreImplementation()
            testsImplementation()
        }
    }

    private fun Project.tasks() {
    }
}

fun Project.appModule(configure: Action<BaseAppModuleExtension>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("android", configure)
