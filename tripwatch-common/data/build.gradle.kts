plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.20")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
}

tasks.named<Jar>("jar") {
    from(sourceSets.named("main").get().output)
    archiveFileName.set("tripwatch-common-data.jar")
}
