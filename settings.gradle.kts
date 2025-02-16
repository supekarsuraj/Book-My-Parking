pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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
    versionCatalogs {
        create("libs") {
            version("agp", "8.2.0")
            version("kotlin", "1.9.10")

            plugin("android-application", "com.android.application").versionRef("agp")
            plugin("kotlin-android", "org.jetbrains.kotlin.android").versionRef("kotlin")
            plugin("google-services", "com.google.gms.google-services").version("4.4.0")
        }
    }
}

rootProject.name = "BookMyParking"
include(":app")