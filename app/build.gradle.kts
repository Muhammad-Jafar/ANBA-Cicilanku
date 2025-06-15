plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.nav.safe.args)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "app.cicilan.app"
    compileSdk = libs.versions.compileSdk.get()
        .toInt()

    defaultConfig {
        applicationId = "app.cicilan.app"
        minSdk = libs.versions.minSdk.get()
            .toInt()
        targetSdk = libs.versions.compileSdk.get()
            .toInt()
        versionCode = 7
        versionName = "1.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
        debug {
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        viewBinding = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":data:local"))
    implementation(project(":data:component"))
    implementation(project(":model:entities"))
    implementation(project(":model:repositories"))
    implementation(project(":navigation"))

    api(libs.bundles.koin)
    api(libs.bundles.commoncore)
    api(libs.bundles.lifecycle)
    api(libs.bundles.activityfragment)
    api(libs.bundles.navcomponent)
    api(libs.bundles.coroutines)
    api(libs.datastore.preference)
    /*api(libs.bundles.room)
    ksp(libs.room.compiler)*/

    /*implementation(libs.constraint.layout)
    implementation(libs.recycleview) // RecycleView
    implementation(libs.viewpager2) // View Pager
    implementation(libs.preference) // Preference Screen Setting
    implementation(libs.splashscreen) // Splashscreen*/

    /*// Unit Testing
    testImplementation(libs.bundles.unittest)

    // Testing
    testImplementation(libs.bundles.uitest)
    testImplementation(libs.bundles.roomtest)
    testImplementation(libs.bundles.coroutinetest)*/

    // Crop Image Library
    //implementation(libs.android.image.cropper)

    // Lottie animation
    // implementation 'com.airbnb.android:lottie:6.1.0'
}
