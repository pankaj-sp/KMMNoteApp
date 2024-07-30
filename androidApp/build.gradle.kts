plugins {
//    alias(libs.plugins.androidApplication)
//    alias(libs.plugins.kotlinAndroid)
//    alias(libs.plugins.jetbrainsKotlinKapt)
    alias(libs.plugins.compose.compiler)
//    alias(libs.plugins.hilt)
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.kmmnoteapp.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.kmmnoteapp.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.6.8"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.viewmodel.android)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.kotlinx.datetime)
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    implementation("com.google.dagger:hilt-android:2.43.2")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    kapt("com.google.dagger:hilt-android-compiler:2.43.2")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
//    implementation(libs.androidx.hilt.navigation.compose)
//
//    implementation(libs.hilt.android)
//    kapt(libs.hilt.android.compiler)
//    kapt(libs.androidx.hilt.compiler)
//    kapt(libs.hilt.compiler)
}