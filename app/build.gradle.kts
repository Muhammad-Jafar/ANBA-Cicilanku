plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
    id("com.google.devtools.ksp")
}

android {
    namespace = "app.cicilan.app"
    compileSdk = 36

    defaultConfig {
        applicationId = "app.cicilan.app"
        minSdk = 25
        targetSdk = 36
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
        jvmTarget = "17"
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
    implementation(project(":data-module:component"))
    implementation(project(":data-module:db"))
    implementation(project(":data-module:preference"))
    implementation(project(":data-module:repository"))
    implementation(project(":data-module:util"))
    implementation(project(":domain-module:entities"))
    implementation(project(":domain-module:repositories"))
    implementation(project(":domain-module:usecases"))
    implementation(project(":presentation-module"))

    api(libs.bundles.koin)

    api(libs.bundles.commoncore)
    api(libs.bundles.lifecycle)
    api(libs.bundles.activityfragment)
    api(libs.bundles.navcomponent)
    api(libs.bundles.coroutines)
    api(libs.bundles.room)
    ksp(libs.room.ksp)

    // Unit Testing
    testImplementation(libs.bundles.unittest)

    // Testing
    testImplementation(libs.bundles.uitest)
    testImplementation(libs.bundles.roomtest)
    testImplementation(libs.bundles.coroutinetest)

    implementation(libs.constraint.layout)
    implementation(libs.recycleview) // RecycleView

    implementation(libs.datastore.preference) // datastore
    implementation(libs.viewpager2) // View Pager
    implementation(libs.preference) // Preference Screen Setting
    implementation(libs.splashscreen) // Splashscreen

    // Crop Image Library
    implementation(libs.android.image.cropper)

    // Lottie animation
    // implementation 'com.airbnb.android:lottie:6.1.0'
}
