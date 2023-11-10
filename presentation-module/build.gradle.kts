@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.nav.safe.args)
}

android {
    namespace = "app.cicilan.presentation"
    compileSdk = 34

    defaultConfig {
        minSdk = 25

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
}

dependencies {
    implementation(project(":domain-module:usecases"))
    implementation(project(":domain-module:entities"))
    implementation(project(":domain-module:repositories"))
    implementation(project(":data-module:util"))
    implementation(project(":data-module:component"))

    implementation(libs.bundles.koin)
    implementation(libs.bundles.commoncore)
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.activityfragment)
    implementation(libs.bundles.navcomponent)
    implementation(libs.bundles.coroutines)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.constraint.layout)
    implementation(libs.recycleview) // RecycleView

    implementation(libs.datastore.preference) // datastore
    implementation(libs.viewpager2) // View Pager
    implementation(libs.preference) // Preference Screen Setting
    implementation(libs.splashscreen) // Splashscreen

    // Crop Image Library
    implementation(libs.android.image.cropper)
}
