plugins {
    kotlin("jvm")
    id("java-gradle-plugin")
    id("com.gradle.plugin-publish")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(path = ":core", configuration = "default"))
    implementation(gradleApi())

    testImplementation("junit:junit:4.13.1")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

gradlePlugin {
    plugins {
        create("dev.kmmresources.localize") {
            id = "dev.kmmresources.localize"
            implementationClass = "dev.kmmresources.plugin.KmmResourcesPlugin"
            version = "1.0.0"
        }
    }
}

// Configuration Block for the Plugin Marker artifact on Plugin Central
pluginBundle {
    website = "https://github.com/cortinico/kotlin-gradle-plugin-template"
    vcsUrl = "https://github.com/cortinico/kotlin-gradle-plugin-template"
    description = "An empty Gradle plugin created from a template"
    tags = listOf(
        "plugin",
        "gradle",
        "sample",
        "template"
    )


    plugins {
        getByName("dev.kmmresources.localize") {
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
