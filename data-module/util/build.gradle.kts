android {
    namespace = "app.cicilan.util"
    kotlinOptions {
        freeCompilerArgs += "-Xcontext-receivers"
    }
}

dependencies {
    implementation(libs.bundles.commoncore)
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.activityfragment)

}
