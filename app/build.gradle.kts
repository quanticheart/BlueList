plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("app-plugin")
    id("com.gladed.androidgitversion")
}

android {
    androidGitVersion {
        baseCode = 200000
        codeFormat = "MNNPPP"
        format = "%tag%%-commit%%-dirty%"
        untrackedIsDirty = true
    }

    defaultConfig {
        versionName = androidGitVersion.name()
        versionCode = androidGitVersion.code()
        base.archivesName.set("${applicationId}-v${versionName}-(${versionCode})")
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":data"))
}