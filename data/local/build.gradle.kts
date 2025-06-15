plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "app.cicilan.local"
    compileSdk = libs.versions.compileSdk.get()
        .toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get()
            .toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    implementation(project(":model:entities"))

    implementation(libs.core.ktx)
    implementation(libs.bundles.datastore)
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    testImplementation(libs.bundles.roomtest)
}
