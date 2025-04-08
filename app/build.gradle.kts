plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.tatacodechallenge"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.tatacodechallenge"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    dataBinding{
        enable = true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.databinding.runtime)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // ViewModel and LiveData (Lifecycle)
    implementation( "androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation( "androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
    implementation( "androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation( "androidx.activity:activity-ktx:1.10.1")

    // RecyclerView
    implementation( "androidx.recyclerview:recyclerview:1.4.0")

    // Room Database
    implementation( "androidx.room:room-runtime:2.6.1")
//    kapt("androidx.room:room-compiler:2.6.1")
    implementation( "androidx.room:room-ktx:2.6.1")

    // Kotlin Coroutines
    implementation( "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation( "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
}