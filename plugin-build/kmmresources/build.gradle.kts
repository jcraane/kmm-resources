plugins {
    kotlin("jvm")
    id("java-gradle-plugin")
    `maven-publish`
    id("com.gradle.plugin-publish")
}

val junitVersion: String by project
val website: String by project
val vcsUrl: String by project
val description: String by project

dependencies {
    implementation(kotlin("stdlib-jdk8"))
//    compile(project(path = ":core", configuration = "default"))
    compile("org.yaml:snakeyaml:1.27")
    implementation(gradleApi())

//    testImplementation(junitVersion)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "kmmresources"
            pom {
                name.set("KMM Resources plugin")
                description.set("Gradle plugin for generating localizable resources for Android and iOS in a Kotlin Multiplatform Mobile project")
                url.set("https://github.com/jcraane/kmm-resources")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("jcraane")
                        name.set("Jamie Craane")
                    }
                    developer {
                        id.set("lammertw")
                        name.set("Lammert Westerhoff")
                    }
                }
                scm {
                    connection.set("https://github.com/jcraane/kmm-resources.git")
                    developerConnection.set("https://github.com/jcraane/kmm-resources.git")
                    url.set("https://github.com/jcraane/kmm-resources")
                }
            }
        }
    }
}

gradlePlugin {
    plugins {
        create("com.capoax.kmmresources") {
            id = "com.capoax.kmmresources"
            implementationClass = "com.capoax.kmmresources.plugin.KmmResourcesPlugin"
            version = "1.0.0-alpha01"
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
