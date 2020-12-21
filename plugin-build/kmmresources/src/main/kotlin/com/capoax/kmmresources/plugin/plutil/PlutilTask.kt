package com.capoax.kmmresources.plugin.plutil

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

abstract class PlutilTask : DefaultTask() {
    @get:Input
    @get:Option(option = "srcFolder", description = "Path to the generated iOS resources (Localizable.string), defaults to 'src'")
    abstract val srcFolder: Property<String>

    @TaskAction
    fun plutil() {
        val pathToIosResources = project.projectDir.resolve(srcFolder.get()).resolve("commonMain/resources/ios")

        pathToIosResources.walkTopDown()
            .filter { it.name == LOCALIZABLE_FILE_NAME }
            .map { it.absolutePath }
            .forEach { aFile ->
                println(aFile)
                project.exec {
                    it.workingDir = project.projectDir
                    it.commandLine = listOf("plutil", "-convert", "binary1", aFile)
                }
            }
    }

    companion object {
        const val NAME = "plutil"
        private const val LOCALIZABLE_FILE_NAME = "Localizable.strings"
    }
}
