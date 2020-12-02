plugins {
    kotlin("jvm") version "1.4.20"
    kotlin("plugin.serialization") version "1.4.20" apply false
}

allprojects {
    group = "com.johnturkson.tripwatch"
    version = "0.0.1"
    
    repositories {
        mavenCentral()
        jcenter()
        maven("https://maven.pkg.jetbrains.space/johnturkson/p/packages/maven")
    }
}
