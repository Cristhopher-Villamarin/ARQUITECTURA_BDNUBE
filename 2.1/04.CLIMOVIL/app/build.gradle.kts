plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "ec.edu.monster"
    compileSdk = 35

    defaultConfig {
        applicationId = "ec.edu.monster"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation("androidx.activity:activity:1.8.2")
    implementation("androidx.core:core-ktx:1.16.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.13.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")

    // Opcional: Para RecyclerView, si lo necesitas
    implementation("androidx.recyclerview:recyclerview:1.4.0")

    implementation("com.squareup.okhttp3:okhttp:5.2.1")  // Actualizado a la última estable

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}