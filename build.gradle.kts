plugins {
    kotlin("jvm") version "1.9.23"
    id("com.gradle.plugin-publish") version "1.3.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

group = "io.github.theaminnouri"
version = "0.4"

gradlePlugin {
    website = "https://github.com/theaminnouri/android-versioning-gradle-plugin"
    vcsUrl = "https://github.com/theaminnouri/android-versioning-gradle-plugin.git"
    plugins {
        create("android-versioning-plugin") {
            id = "io.github.theaminnouri.android-versioning-plugin"
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