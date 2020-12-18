package dev.kmmresources.plugin

import org.gradle.api.Project
import org.gradle.api.provider.Property
import javax.inject.Inject
import java.io.File

abstract class KmmResourcesExtension @Inject constructor(project: Project) {
    private val objects = project.objects

    val input: Property<File> = objects.property(File::class.java)

    val output: Property<File> = objects.property(File::class.java)

    val androidRPackage: Property<String> = objects.property(String::class.java)

    val androidDefaultLanguage: Property<String> = objects.property(String::class.java)

    val packageName: Property<String> = objects.property(String::class.java)

    val sharedModuleName: Property<String> = objects.property(String::class.java)
}

