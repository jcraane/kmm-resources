package dev.kmmresources.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File

open class GenerateLocalizationsTask : DefaultTask() {
    @Input
    lateinit var localizationFile: File

    init {
        description = "Generate localizations task"

        println("ININITNI")
    }

    @TaskAction
    fun localize() {
        println("Here we do the actual work")
    }
}
