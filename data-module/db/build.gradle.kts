android {
    namespace = "app.cicilan.db"
}

dependencies {
    implementation(project(":domain-module:entities"))
    implementation(libs.bundles.commoncore)
    implementation(libs.bundles.room)

    testImplementation(libs.bundles.roomtest)
}
