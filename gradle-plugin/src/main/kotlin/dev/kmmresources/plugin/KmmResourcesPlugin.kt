package dev.kmmresources.plugin

import dev.kmmresources.core.LocalizationGenerator
import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import java.io.File

class KmmResourcesPlugin: Plugin<Project> {

    @InputDirectory
    lateinit var repoLocation: File

    @Input
    lateinit var localizationFile: File

    @Input
    lateinit var androidRPackage: String

    @Input
    lateinit var targetPackage: String

    @InputDirectory
    lateinit var commonFolder: String

    override fun apply(project: Project) {
        // Register a task
        project.tasks.register("generate-kmm-resources") { task ->
            task.doLast {
                val generator = LocalizationGenerator(localizationFile, repoLocation.parentFile, androidRPackage, targetPackage, commonFolder)
                generator.generate()
            }
        }
    }
}
