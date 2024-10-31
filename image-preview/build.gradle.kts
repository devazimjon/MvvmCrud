import com.android.build.gradle.internal.packaging.fromProjectProperties
import io.grpc.internal.SharedResourceHolder.release

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.example.image_preview"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
    kotlinOptions {
        jvmTarget = "19"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
}

dependencies {
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.material3)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.devazimjon"
            artifactId = "image-preview-compose"
            version = "1.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}