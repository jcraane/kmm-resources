package dev.kmmresources.plugin

import dev.kmmresources.core.LocalizationGenerator
import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import java.io.File

const val EXTENSION_NAME = "kmmResourcesConfig"
const val TASK_NAME = "generateLocalizations"

class KmmResourcesPlugin: Plugin<Project> {

    /*@InputDirectory
    lateinit var repoLocation: File

    @Input
    lateinit var localizationFile: File

    @Input
    lateinit var androidRPackage: String

    @Input
    lateinit var targetPackage: String

    @InputDirectory
    lateinit var commonFolder: String*/

    /**
     * Entry point of the plugin which Gradle calls when the plugin is applied to the project.
     */
    override fun apply(project: Project) {
        println("APPLIED HERE")
        val extension = project.extensions.create(EXTENSION_NAME, KmmResourcesExtension::class.java, project)

        project.tasks.register(TASK_NAME, GenerateLocalizationsTask::class.java) {
            println("INIT TASK")
            it.message.set(extension.message)
        }
    }
}
