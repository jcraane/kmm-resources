package com.capoax.kmmresources.plugin

import org.gradle.api.Project
import org.gradle.api.Plugin

const val EXTENSION_NAME = "kmmResourcesConfig"
const val TASK_NAME = "generateLocalizations"

class KmmResourcesPlugin: Plugin<Project> {
    /**
     * Entry point of the plugin which Gradle calls when the plugin is applied to the project.
     */
    override fun apply(project: Project) {
        val extension = project.extensions.create(EXTENSION_NAME, KmmResourcesExtension::class.java, project)

        project.tasks.register(TASK_NAME, GenerateLocalizationsTask::class.java) {
            it.androidRPackage.set(extension.androidRPackage)
            it.androidDefaultLanguage.set(extension.androidDefaultLanguage)
            it.input.set(extension.input)
            it.output.set(extension.output)
            it.sharedModuleName.set(extension.sharedModuleName)
            it.packageName.set(extension.packageName)
            it.androidStringsPrefix.set(extension.androidStringsPrefix)
        }
    }
}
