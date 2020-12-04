plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.20")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    implementation("com.amazonaws:aws-lambda-java-core:1.2.1")
}

tasks.register<Copy>("copyLambdaLayerDependencies") {
    from(configurations.runtimeClasspath)
    into("$buildDir/lambda/layer/java/lib")
}

tasks.register<Zip>("buildLambdaLayer") {
    archiveFileName.set("tripwatch-kotlin-lambda-layer.zip")
    destinationDirectory.set(file("$buildDir/lambda"))
    from("$buildDir/lambda/layer")
    dependsOn("copyLambdaLayerDependencies")
}
