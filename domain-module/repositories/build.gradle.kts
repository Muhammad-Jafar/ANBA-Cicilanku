android {
    namespace = "app.cicilan.repositories"
}

dependencies {
    implementation(project(":data-module:db"))
    implementation(project(":data-module:preference"))
    implementation(project(":domain-module:entities"))

    implementation(libs.bundles.commoncore)

}
