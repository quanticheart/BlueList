package extentions

import org.gradle.api.artifacts.dsl.DependencyHandler

//util functions for adding the different type dependencies from build.gradle.kts file
@Suppress("unused")
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.kapt(dependency: String) {
    add("kapt", dependency)
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.implementation(dependency: String) {
    add("implementation", dependency)
}

fun DependencyHandler.implementation(dependency: Any) {
    add("implementation", dependency)
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}
//
//fun Project.variant(
//    debugVariant: () -> Unit,
//    releaseVariant: () -> Unit
//) {
//    if (!moduleIsLibrary()) {
//        project.extensions.configure<AppExtension>("android") {
//            applicationVariants.all {
//                when (buildType.name) {
//                    debug -> debugVariant()
//                    release -> releaseVariant()
//                }
//            }
//        }
//    } else {
//        project.extensions.configure<LibraryExtension>("android") {
//            libraryVariants.all {
//                when (buildType.name) {
//                    debug -> debugVariant()
//                    release -> releaseVariant()
//                }
//            }
//        }
//    }
//}
