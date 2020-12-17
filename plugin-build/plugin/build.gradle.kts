plugins {
    kotlin("jvm")
    id("java-gradle-plugin")
    id("com.gradle.plugin-publish")
}

val junitVersion: String by project
val website: String by project
val vcsUrl: String by project
val description: String by project

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(path = ":core", configuration = "default"))
    implementation(gradleApi())

    testImplementation(junitVersion)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

gradlePlugin {
    plugins {
        create("com.capoax.kmmresources") {
            id = "com.capoax.kmmresources"
            implementationClass = "dev.kmmresources.plugin.KmmResourcesPlugin"
            version = "1.0.0"
        }
    }
}

// Configuration Block for the Plugin Marker artifact on Plugin Central
pluginBundle {
    website = website
    vcsUrl = vcsUrl
    description = description
    tags = listOf(
        "plugin",
        "gradle",
        "sample",
        "template"
    )


    plugins {
        getByName("com.capoax.kmmresources") {
            displayName = "An empty Gradle Plugin from a template"
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
