package dev.kmmresources.plugin

import org.gradle.api.Project
import org.gradle.api.provider.Property
import javax.inject.Inject

abstract class KmmResourcesExtension @Inject constructor(project: Project) {
    private val objects = project.objects

    val message: Property<String> = objects.property(String::class.java)
}
