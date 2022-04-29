package extentions

import App
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

val Project.android: BaseExtension
    get() = extensions.findByName("android") as? BaseExtension
        ?: error("Not an Android module: $name")

fun Project.moduleIsLibrary(): Boolean {
    return pluginManager.findPlugin("com.android.library")?.let {
        true
    } ?: pluginManager.findPlugin("java-library")?.let {
        true
    } ?: false
}

/**
 * App versions
 *
 * @param moduleIsLibrary
 */
fun BaseExtension.appVersionsOnlyModule(moduleIsLibrary: Boolean) {
    compileSdkVersion(App.compileSdk)
    defaultConfig {
        resourceConfigurations.add("pt")
        minSdk = App.minSdk
        targetSdk = App.targetSdk

        if (!moduleIsLibrary) {
            applicationId = App.id
        } else {
            versionCode = App.versionCode
            versionName = App.versionName
            consumerProguardFiles(App.proguardConsumerRules)
        }
        testInstrumentationRunner = App.androidTestInstrumentation
    }
}

/**
 * App build types
 *
 * @param androidIsLibrary
 */
fun BaseExtension.appBuildTypes(androidIsLibrary: Boolean) {
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true

            splits.abi.isEnable = false
            splits.density.isEnable = false
            aaptOptions.cruncherEnabled = false

            proguardFiles(
                getDefaultProguardFile(App.proguardAndroidOptimize),
                App.proguardConsumerRules
            )

            if (!androidIsLibrary) {
                val data = mapOf(
                    "labelName" to "@string/app_name_debug",
                    "appIcon" to "@mipmap/ic_launcher_debug"
                )
                manifestPlaceholders["labelName"] = "${data["labelName"]}"
                manifestPlaceholders["appIcon"] = "${data["appIcon"]}"
                applicationIdSuffix = ".debug"
                signingConfig = signingConfigs.getByName("debug")
                resValue(
                    "string",
                    "file_provider_authorities",
                    "blue.page.files.debug.fileprovider"
                )
            }
        }

        getByName("release") {
            ndk {
                abiFilters.addAll(arrayListOf("x86", "x86_64", "arm64-v8a", "armeabi-v7a"))
            }
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile(App.proguardAndroidOptimize),
                App.proguardConsumerRules
            )
            if (!androidIsLibrary) {
                isShrinkResources = true
                val data = mapOf(
                    "labelName" to "@string/app_name",
                    "appIcon" to "@mipmap/ic_launcher"
                )
                manifestPlaceholders["labelName"] = "${data["labelName"]}"
                manifestPlaceholders["appIcon"] = "${data["appIcon"]}"
                signingConfig = signingConfigs.getByName("release")
                resValue(
                    "string",
                    "file_provider_authorities",
                    "blue.page.files.fileprovider"
                )
            }
        }
    }
}

/**
 * Java version
 */
private val javaVersion = JavaVersion.VERSION_1_8

/**
 * App compile options
 *
 */
fun BaseExtension.appCompileOptions() {
    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
}
