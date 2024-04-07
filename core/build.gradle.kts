plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    // untuk parcelize
    id("kotlin-parcelize")

    // untuk ksp room
    id("com.google.devtools.ksp")

    // untuk kapt
    id("kotlin-kapt")
}
apply("../shared_dependencies.gradle")

android {
    namespace = "com.dicoding.capstone.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // sqlcipher
    implementation("net.zetetic:android-database-sqlcipher:4.4.0")
    implementation("androidx.sqlite:sqlite-ktx:2.3.1")

    // room database
    implementation("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    androidTestImplementation("androidx.room:room-testing:2.6.1")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // kotlin stdlib
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.20")
}