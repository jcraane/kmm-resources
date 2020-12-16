plugins {
    kotlin("jvm") version "1.3.72"
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
    implementation(kotlin("stdlib", "1.3.72"))
}

kmmResourcesConfig {
    message.set("Hallo23")
}

val example = tasks["generateLocalizations"]
tasks["build"].dependsOn(example)
println("name = ${tasks["generateLocalizations"].name}")
