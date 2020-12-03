plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "com.johnturkson.tripwatch"
        minSdkVersion(26)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner("android.support.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        release {
            minifyEnabled(false)
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerVersion = "1.4.20"
        kotlinCompilerExtensionVersion = "1.0.0-alpha08"
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.20")
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    
    implementation("androidx.compose.runtime:runtime:1.0.0-alpha08")
    implementation("androidx.compose.ui:ui:1.0.0-alpha08")
    implementation("androidx.compose.ui:ui-tooling:1.0.0-alpha08")
    implementation("androidx.compose.foundation:foundation:1.0.0-alpha08")
    implementation("androidx.compose.material:material:1.0.0-alpha08")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    androidTestImplementation("com.android.support.test:runner:1.0.2")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")
    androidTestImplementation("androidx.compose.ui:ui-test:1.0.0-alpha08")
}
