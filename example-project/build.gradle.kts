plugins {
    kotlin("jvm")
    `java-library`
    id("com.capoax.kmmresources")
}

repositories {
    // Use JCenter for resolving dependencies.
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation(kotlin("stdlib", "1.3.72"))
}

kmmResourcesConfig {
    androidRPackage.set("com.localizations")
    packageName.set("com.example.project")
    androidDefaultLanguage.set("nl")
    input.set(File(project.projectDir.path, "generic_small.yaml"))
    output.set(project.projectDir.resolve("build").resolve("test_output"))
    androidStringsPrefix.set("shared_")
}

val generateLocalizations = tasks["generateLocalizations"]
tasks["build"].dependsOn(generateLocalizations)
println("name = ${tasks["generateLocalizations"].name}")
