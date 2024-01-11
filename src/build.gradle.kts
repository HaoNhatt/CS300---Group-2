// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.6.0")
        classpath("com.google.gms:google-services:4.4.0")

    }
}

plugins {
    id("com.android.application") version "8.2.1" apply false
    id ("com.android.library") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.20" apply false
    id("com.google.devtools.ksp") version "1.8.20-1.0.10" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
}
