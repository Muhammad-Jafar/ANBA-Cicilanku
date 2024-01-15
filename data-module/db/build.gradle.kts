android {
    namespace = "app.cicilan.db"
}

dependencies {
    implementation(project(":domain-module:entities"))
    implementation(libs.bundles.commoncore)
    api(libs.bundles.room)

    testImplementation(libs.bundles.roomtest)
}
