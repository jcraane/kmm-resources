package dev.kmmresources.plugin

import dev.kmmresources.MyTestClass
import dev.kmmresources.core.LocalizationGenerator
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import java.io.ByteArrayInputStream
import java.io.File

abstract class GenerateLocalizationsTask : DefaultTask() {
    /*@Input
    lateinit var localizationFile: File*/

    @get:Input
    @get:Option(option = "message", description = "Printed to console")
    abstract val message: Property<String>

    init {
        description = "Generate localizations task"
        println("INIT TASK2")
    }

    @TaskAction
    fun localize() {
        println(MyTestClass().greet())
        println("Message = ${message.get()}")
    }
}
