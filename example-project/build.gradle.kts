plugins {
    kotlin("jvm")
    `java-library`
    id("com.capoax.kmmresources")
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
    androidRPackage.set("com.localizations")
    sharedModuleName.set("shared")
    packageName.set("com.example.project")
    androidDefaultLanguage.set("nl")
    input.set(File(project.projectDir.path, "generic_small.yaml"))
    output.set(project.projectDir.resolve("build").resolve("test_output"))
}

val example = tasks["generateLocalizations"]
tasks["build"].dependsOn(example)
println("name = ${tasks["generateLocalizations"].name}")
