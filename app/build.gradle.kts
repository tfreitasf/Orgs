plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")

}

android {
    namespace = "br.com.povengenharia.orgs"
    compileSdk = 34

    defaultConfig {
        applicationId = "br.com.povengenharia.orgs"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas".toString()
            }
        }

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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("io.coil-kt:coil:2.5.0")
    implementation("io.coil-kt:coil-gif:2.5.0")

    val room_version = "2.6.0"
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation ("androidx.room:room-ktx:$room_version")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")


    val lifecycle_version = "2.4.0"
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")

    implementation("androidx.datastore:datastore-preferences:1.0.0")



}