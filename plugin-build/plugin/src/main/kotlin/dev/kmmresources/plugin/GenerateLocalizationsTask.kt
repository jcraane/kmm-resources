package dev.kmmresources.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import java.io.File

abstract class GenerateLocalizationsTask : DefaultTask() {
    /*@Input
    lateinit var localizationFile: File*/

    @get:Input
    @get:Option(option = "message", description = "Printed to console")
    abstract val message: Property<String>

    init {
        description = "Generate localizations task"
        println("INIT TASK")
    }

    @TaskAction
    fun localize() {
        println("Message = ${message.get()}")
    }
}
