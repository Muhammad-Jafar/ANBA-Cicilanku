android {
    namespace = "app.cicilan.di"
    kotlinOptions {
        freeCompilerArgs += "-Xcontext-receivers"
    }
}

dependencies {
    implementation(project(":data-module:db"))
    implementation(project(":data-module:preference"))
    implementation(project(":data-module:repository"))
    implementation(project(":domain-module:repositories"))
    implementation(project(":domain-module:usecases"))

    implementation(libs.bundles.commoncore)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.datastore)
    implementation(libs.bundles.room)
    ksp(libs.room.ksp)

    testImplementation(libs.bundles.unittest)
    testImplementation(libs.bundles.uitest)
}
