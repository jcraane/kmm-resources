plugins {
    kotlin("jvm")
    id("java-gradle-plugin")
    `maven-publish`
    id("com.gradle.plugin-publish")
}

val junitVersion: String by project
val description = "Gradle plugin for generating localizable resources for Android and iOS in a Kotlin Multiplatform Mobile project for use in the UI, android, iOS and shared framework code."

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compile("org.yaml:snakeyaml:1.27")
    implementation(gradleApi())
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

gradlePlugin {
    plugins {
        create("KmmResources") {
            id = "dev.jamiecraane.plugins.kmmresources"
            implementationClass = "com.capoax.kmmresources.plugin.KmmResourcesPlugin"
            description = description
            version = "1.0.0-alpha11"
        }
    }
}

// Configuration Block for the Plugin Marker artifact on Plugin Central
pluginBundle {
    website = "https://github.com/jcraane/kmm-resources"
    vcsUrl = "https://github.com/jcraane/kmm-resources.git"
    description = "Gradle plugin for generating localizable resources for Android and iOS in a Kotlin Multiplatform Mobile project for use in the UI, android, iOS and shared framework code."
    tags = listOf(
        "plugin",
        "gradle",
        "kmm",
        "multiplatform",
        "android",
        "ios"
    )


    plugins {
        getByName("KmmResources") {
            displayName = "Gradle plugin for generating localizable resources for Android and iOS in a Kotlin Multiplatform Mobile project"
        }
    }
}

tasks.create("setupPluginUploadFromEnvironment") {
    doLast {
        val key = System.getenv("GRADLE_PUBLISH_KEY")
        val secret = System.getenv("GRADLE_PUBLISH_SECRET")

        if (key == null || secret == null) {
            throw GradleException("gradlePublishKey and/or gradlePublishSecret are not defined environment variables")
        }

        System.setProperty("gradle.publish.key", key)
        System.setProperty("gradle.publish.secret", secret)
    }
}
