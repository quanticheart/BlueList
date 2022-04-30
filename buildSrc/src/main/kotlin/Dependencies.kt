import extentions.androidTestImplementation
import extentions.implementation
import extentions.kapt
import extentions.testImplementation
import org.gradle.kotlin.dsl.DependencyHandlerScope

object Dependencies {
    /**
     * Kotlin std lib
     */
    val kotlinLibs = arrayListOf<String>().apply {
        add("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")
    }

    /**
     * Koin
     */
    private const val koin_android = "io.insert-koin:koin-android:3.2.0-beta-1"

    val dependencyInjectionLibraries = arrayListOf<String>().apply {
        add(koin_android)
    }

    /**
     * UI
     */
    private const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private const val core = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.1.3"
    private const val recyclerview = "androidx.recyclerview:recyclerview:1.2.1"
    private const val cardview = "androidx.cardview:cardview:1.0.0"
    private const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
    private const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    private const val lifecycle_run_time =
        "androidx.lifecycle:lifecycle-runtime:2.4.1"
    private const val lifecycle_viewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1"
    private const val navigation_fragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    private const val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    private const val lottie = "com.airbnb.android:lottie:5.1.1"

    /**
     * Google
     */
    private const val material = "com.google.android.material:material:1.5.0"

    val appLibraries = arrayListOf<String>().apply {
        add(appcompat)
        add(core)
        add(lottie)
        add(constraintlayout)
        add(recyclerview)
        add(cardview)
        add(swiperefreshlayout)
        add(lifecycle)
        add(lifecycle_run_time)
        add(lifecycle_viewmodel)
        add(navigation_fragment)
        add(navigation_ui)
        add(material)
    }

    /**
     * Permissionsdispatcher
     */
    internal const val permissionsDispatcher =
        "com.github.permissions-dispatcher:permissionsdispatcher:4.9.2"
    internal const val permissionsDispatcherKapt =
        "com.github.permissions-dispatcher:permissionsdispatcher-processor:4.9.2"

    /**
     * Firebase bom
     */
//    internal const val firebaseBom = "com.google.firebase:firebase-bom:29.1.0"
//    private const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
//    private const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx"
//    private const val firebaseCore = "com.google.firebase:firebase-core:17.5.0"

    val firebaseLibraries = arrayListOf<String>().apply {
//        add(firebaseAnalytics)
//        add(firebaseCrashlytics)
//        add(firebaseCore)
    }

    /**
     * Retrofit
     */
    private const val logging = "com.squareup.okhttp3:logging-interceptor:4.2.2"
    private const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private const val gson = "com.google.code.gson:gson:2.8.6"
    private const val retrofit2_converter =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    val connLibraries = arrayListOf<String>().apply {
        add(logging)
        add(retrofit2)
        add(retrofit2_converter)
        add(gson)
    }

    /**
     * Room
     */
    private const val room = "androidx.room:room-runtime:${Versions.room}"
    private const val room_coroutine = "androidx.room:room-ktx:${Versions.room}"
    private const val roomTest = "androidx.room:room-testing:${Versions.room}"
    internal const val kaptRoom = "androidx.room:room-compiler:${Versions.room}"

    val databaseLibraries = arrayListOf<String>().apply {
        add(room)
        add(room_coroutine)
    }

    /**
     * Commons - Core
     */
    internal const val glideCompiler = "com.github.bumptech.glide:compiler:4.13.1"
    private const val glide = "com.github.bumptech.glide:glide:4.13.1"
    private const val timber = "com.jakewharton.timber:timber:5.0.1"

    val commonsLibraries = arrayListOf<String>().apply {
        add(glide)
        add(timber)
    }

    /**
     * Tests
     */
    private const val junit = "junit:junit:${Versions.junit}"
    private const val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
    }
}

/**
 * Default implementation
 *
 */

fun DependencyHandlerScope.uiImplementation() {
    implementation(Dependencies.appLibraries)
    implementation(Dependencies.permissionsDispatcher)
    kapt(Dependencies.permissionsDispatcherKapt)
}

fun DependencyHandlerScope.kotlinImplementation() {
    implementation(Dependencies.kotlinLibs)
}

fun DependencyHandlerScope.dependencyInjectionImplementation() {
    implementation(Dependencies.dependencyInjectionLibraries)
}

fun DependencyHandlerScope.connectionImplementation() {
    implementation(Dependencies.connLibraries)
}

fun DependencyHandlerScope.databaseImplementation() {
    implementation(Dependencies.databaseLibraries)
    kapt(Dependencies.kaptRoom)
}

fun DependencyHandlerScope.googleImplementation() {
//    implementation(Dependencies.googleLibraries)
}

fun DependencyHandlerScope.firebaseCoreImplementation() {
//    implementation(platform(Dependencies.firebaseBom))
//    implementation(Dependencies.firebaseLibraries)
}

fun DependencyHandlerScope.coreImplementation() {
    implementation(Dependencies.commonsLibraries)
    kapt(Dependencies.glideCompiler)
}

fun DependencyHandlerScope.testsImplementation() {
    androidTestImplementation(Dependencies.androidTestLibraries)
    testImplementation(Dependencies.testLibraries)
}
