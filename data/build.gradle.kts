plugins {
    id("com.android.library")
    id("app-plugin")
}

dependencies {
    databaseImplementation()
    implementation(project(":core"))
    implementation(project(":domain"))
}