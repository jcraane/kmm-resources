plugins {
    kotlin("jvm") version "1.4.20"
    `java-library`
    id("dev.kmmresources.localize")
}

/*buildscript {
    dependencies {
        classpath(project(":gradle-plugin"))
    }
}*/

repositories {
    // Use JCenter for resolving dependencies.
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation(kotlin("stdlib", "1.4.20"))
}

kmmResourcesConfig {
    message.set("Hallo23")
}

val example = tasks["generateLocalizations"]
tasks["build"].dependsOn(example)
println("name = ${tasks["generateLocalizations"].name}")
