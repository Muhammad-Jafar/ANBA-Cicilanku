android {
    namespace = "app.cicilan.entities"
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.bundles.commoncore)
    implementation(libs.bundles.room)
    ksp(libs.room.ksp)

}
