android {
    namespace = "app.cicilan.customview"
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.bundles.commoncore)
    implementation(libs.preference)
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.activityfragment)
    implementation(libs.constraint.layout)
}
