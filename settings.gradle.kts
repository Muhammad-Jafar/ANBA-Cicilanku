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
include(":navigation")
include(":data:local")
include(":data:component")
include(":model:entities")
include(":model:repositories")
include(":model:usecases")
