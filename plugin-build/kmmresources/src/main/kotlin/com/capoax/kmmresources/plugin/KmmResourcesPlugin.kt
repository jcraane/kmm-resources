package com.capoax.kmmresources.plugin

import com.capoax.kmmresources.plugin.plutil.PlutilTask
import org.gradle.api.Project
import org.gradle.api.Plugin

class KmmResourcesPlugin: Plugin<Project> {
    /**
     * Entry point of the plugin which Gradle calls when the plugin is applied to the project.
     */
    override fun apply(project: Project) {
        val extension = project.extensions.create(KmmResourcesExtension.NAME, KmmResourcesExtension::class.java, project)

        registerLocalizationsTask(project, extension)
        registerPlutilTask(project, extension)
    }

    private fun registerPlutilTask(project: Project, extension: KmmResourcesExtension) {
        project.tasks.register(PlutilTask.NAME, PlutilTask::class.java) {
            println("extension.srcFolder = ${extension.srcFolder.get()}")
            it.srcFolder.set(extension.srcFolder)
        }
    }

    private fun registerLocalizationsTask(project: Project, extension: KmmResourcesExtension) {
        project.tasks.register(GenerateLocalizationsTask.NAME, GenerateLocalizationsTask::class.java) {
            it.androidApplicationId.set(extension.androidApplicationId)
            it.defaultLanguage.set(extension.defaultLanguage)
            it.androidSourceFolder.set(extension.androidSourceFolder)
            it.iosSourceFolder.set(extension.iosSourceFolder)
            it.input.set(extension.input)
            it.output.set(extension.output)
            it.packageName.set(extension.packageName)
            it.androidStringsPrefix.set(extension.androidStringsPrefix)
            it.srcFolder.set(extension.srcFolder)
            it.generatedClassName.set(extension.generatedClassName)
        }
    }
}
