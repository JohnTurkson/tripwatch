plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.20")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    implementation("com.amazonaws:aws-lambda-java-core:1.2.1")
    implementation("org.springframework.security:spring-security-crypto:5.4.2")
    implementation("org.springframework:spring-jcl:5.3.1")
    implementation("com.johnturkson.aws-tools:aws-dynamodb-object-builder:0.0.37")
    implementation("com.johnturkson.aws-tools:aws-dynamodb-request-builder:0.0.37")
    implementation("com.johnturkson.aws-tools:aws-dynamodb-transforming-serializer:0.0.37")
    implementation("com.johnturkson.aws-tools:aws-dynamodb-request-handler:0.0.37")
    implementation("com.johnturkson.aws-tools:aws-request-signer:0.0.37")
    implementation("com.johnturkson.aws-tools:aws-request-handler:0.0.37")
    implementation("com.johnturkson.aws-tools:aws-ses-request-builder:0.0.37")
    implementation("com.johnturkson.aws-tools:aws-ses-request-handler:0.0.37")
    implementation(project(":tripwatch-common:data"))
}

tasks.named<DefaultTask>("build") {
    dependsOn("buildLambdaLayer")
    dependsOn("buildLambdaFunctions")
}

tasks.register<Zip>("buildLambdaLayer") {
    archiveFileName.set("TripWatchKotlinLambdaLayer.zip")
    destinationDirectory.set(file("$buildDir/lambda/layers"))
    from(configurations.runtimeClasspath)
    into("java/lib")
}

tasks.register<Zip>("buildLambdaFunctions") {
    archiveFileName.set("TripWatchKotlinLambdaFunctions.zip")
    destinationDirectory.set(file("$buildDir/lambda/functions"))
    from(tasks.named("compileKotlin"))
    from(tasks.named("processResources"))
    into("")
}
