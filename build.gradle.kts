plugins {
    kotlin("jvm") version "1.9.23"
    id("com.gradle.plugin-publish") version "1.3.0"
}

repositories {
    google {
        content {
            includeGroupByRegex("com\\.android.*")
            includeGroupByRegex("com\\.google.*")
            includeGroupByRegex("androidx.*")
        }
    }
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:8.5.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

group = "com.theaminnouri"
version = "0.1"

gradlePlugin {
    website = "https://github.com/theaminnouri/android-versioning-gradle-plugin"
    vcsUrl = "https://github.com/theaminnouri/android-versioning-gradle-plugin.git"
    plugins {
        create("android-versioning-plugin") {
            id = "com.theaminnouri.android-versioning-plugin"
            displayName = "Android Versioning gradle plugin"
            description = "This will try streamline android application versioning"
            tags = listOf("Android", "Versioning", "tagVersion", "version")
            implementationClass = "AndroidVersioningPlugin"
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(18)
}