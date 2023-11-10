pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Cicilan"

include(":app")
include(":data-module")
include(":data-module:db")
include(":data-module:component")
include(":data-module:util")
include(":data-module:preference")
include(":data-module:repository")

include(":domain-module")
include(":domain-module:entities")
include(":domain-module:repositories")
include(":domain-module:usecases")
include(":presentation-module")
