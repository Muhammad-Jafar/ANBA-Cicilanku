plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "app.cicilan.repisitories"
    compileSdk = libs.versions.compileSdk.get()
        .toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get()
            .toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        /*consumerProguardFiles("consumer-rules.pro")*/
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        freeCompilerArgs += "-Xcontext-receivers"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":data:component"))
    implementation(project(":data:local"))
    implementation(project(":model:entities"))

    api(libs.core.ktx)
    api(libs.room.runtime)

}
