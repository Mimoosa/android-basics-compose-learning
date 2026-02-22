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
}

rootProject.name = "AndroidoStudioPractice"
include(":app")
include(":app:happy_birthday_card")
include(":app:compose_article")
include(":app:task_manager_complete_page")
include(":app:compose_quadrant")
include(":app:business_card")
include(":app:dice_roll")
include(":app:lemonade_app")
include(":app:lemonade_app_ver2")
include(":app:tip_calculator")
include(":app:art_space_app")
include(":app:number_guessing_game")
include(":app:affirmation")
include(":app:courses_app")
