pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        jcenter()
    }
}

rootProject.name = "kmm-resources-composite-build"

include("example-project")
//include("android-app")
includeBuild("plugin-build")


// With includeBuild somehow we cannot reference plugin (find out why this is)
//includeBuild("KMMForCompositeBuildTest")
/*{
    dependencySubstitution {
        substitute(module("dev.kmmresources.localize:dev.kmmresources.localize.gradle.plugin")).with(project(":kmm-resources-plugin:plugin"))
    }
}*/
