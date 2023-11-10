android {
    namespace = "app.cicilan.repository"
}

dependencies {
    implementation(project(":domain-module:entities"))
    implementation(project(":domain-module:repositories"))
    implementation(project(":data-module:db"))
    implementation(project(":data-module:preference"))
    implementation(project(":data-module:util"))

    implementation(libs.bundles.commoncore)
    implementation(libs.bundles.room)
}
