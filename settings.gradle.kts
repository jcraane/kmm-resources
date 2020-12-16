pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        jcenter()
    }
}

rootProject.name = "kmm-resources"

include("example-project")
includeBuild("plugin-build")
//include("core")
//include("gradle-plugin")
