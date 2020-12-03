buildscript {
    repositories {
        mavenCentral()
        jcenter()
        google()
        maven("https://maven.pkg.jetbrains.space/johnturkson/p/packages/maven")
    }
    
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-alpha01")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.20")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.4.20")
    }
}

allprojects {
    repositories {
        mavenCentral()
        jcenter()
        google()
        maven("https://maven.pkg.jetbrains.space/johnturkson/p/packages/maven")
    }
}
