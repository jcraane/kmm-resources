package com.capoax.kmmresources.plugin

import dev.kmmresources.core.LocalizationGenerator
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.*
import org.gradle.api.tasks.options.Option
import java.io.File
import java.io.FileInputStream

abstract class GenerateLocalizationsTask : DefaultTask() {
    @get:InputFile
    @get:Option(option = "input", description = "Input file containing the localizations")
    abstract val input: Property<File>

    @get:OutputDirectory
    @get:Option(option = "output", description = "Output folder where the generated localizations are written")
    abstract val output: Property<File>

    @get:Input
    @get:Option(option = "androidApplicationId", description = "The applicationId of the Android app. The applicationId is used as the package name for the R class in Android.")
    abstract val androidApplicationId: Property<String>

    @get:Input
    @get:Option(option = "androidDefaultLanguage", description = "The default locale in Android, determines which language is in the values/strings.xml file")
    abstract val androidDefaultLanguage: Property<String>

    @get:Input
    @get:Option(option = "packageName", description = "The package for the generated code")
    abstract val packageName: Property<String>

    @get:Input
    @get:Option(option = "androidStringsPrefix", description = "Prefix which is prepended to the Android generated strings.xml resource files. This is to prevent existing strings.xml from overwriting. Defaults to 'generated_'")
    abstract val androidStringsPrefix: Property<String>

    @get:Input
    @get:Option(option = "srcFolder", description = "The source folder to generate the code to, defaults to 'src'")
    abstract val srcFolder: Property<String>

    init {
        description = "Generate localizations task"
    }

    @TaskAction
    fun localize() {
        val generator = LocalizationGenerator(
            input = FileInputStream(input.get()),
            output = output.get(),
            androidApplicationId = androidApplicationId.get(),
            androidDefaultLanguage = androidDefaultLanguage.get(),
            packageName = packageName.get(),
            androidStringsPrefix = androidStringsPrefix.get()
        )
        generator.generate()
    }
}
