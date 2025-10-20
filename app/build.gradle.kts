// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    kotlin("multiplatform") version "1.5.31"
}

kotlin {
    jvm() // Configure the JVM target
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.gael.blackjack"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
}

// Other configurations...
