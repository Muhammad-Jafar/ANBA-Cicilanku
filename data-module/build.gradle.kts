import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin

subprojects {
    apply(plugin = "com.android.library")
    apply(plugin = "org.jetbrains.kotlin.android")
    /*apply(plugin = "com.google.devtools.ksp")*/

    plugins.withType(LibraryPlugin::class.java).configureEach {
        configure<LibraryExtension> {
            val androidLibrary = extensions.findByName("android") as? LibraryExtension
                ?: error("Project '$name' is not an Android module")

            with(androidLibrary) {
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

                buildFeatures {
                    viewBinding = true
                }
            }
        }
    }
}
