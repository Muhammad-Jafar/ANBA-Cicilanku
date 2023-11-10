android {
    namespace = "app.cicilan.usecases"
}

dependencies {
    implementation(project(":domain-module:entities"))
    implementation(project(":domain-module:repositories"))

    implementation(libs.bundles.commoncore)

}
