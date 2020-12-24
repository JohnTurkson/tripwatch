buildscript {
    repositories {
        mavenCentral()
        jcenter()
        google()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/johnturkson/p/packages/maven")
    }
    
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-alpha03")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.20")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.4.20")
        classpath("org.jetbrains.compose:compose-gradle-plugin:0.2.0-build132")
    }
}

subprojects {
    repositories {
        mavenCentral()
        jcenter()
        google()
        maven("https://maven.pkg.jetbrains.space/johnturkson/p/packages/maven")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
