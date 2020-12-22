package com.capoax.kmmresources.plugin

import org.gradle.api.Project
import org.gradle.api.provider.Property
import javax.inject.Inject
import java.io.File

abstract class KmmResourcesExtension @Inject constructor(project: Project) {
    private val objects = project.objects

    val input: Property<File> = objects.property(File::class.java)

    val output: Property<File> = objects.property(File::class.java)

    val androidApplicationId: Property<String> = objects.property(String::class.java)

    val androidDefaultLanguage: Property<String> = objects.property(String::class.java)

    val packageName: Property<String> = objects.property(String::class.java)

    val androidStringsPrefix: Property<String> = objects.property(String::class.java).apply {
        set("generated_")
    }

    val srcFolder: Property<String> = objects.property(String::class.java).apply {
        set("src")
    }

    val generatedClassName: Property<String> = objects.property(String::class.java).apply {
        set("KMMResourcesLocalization.kt")
    }

    companion object {
        const val NAME = "kmmResourcesConfig"
    }
}

