package com.capoax.kmmresources.plugin

import com.capoax.kmmresources.core.LocalizationGenerator
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
    @get:Option(option = "defaultLanguage", description = "The default locale. Used in Android to determine which language is in the values/strings.xml file. Used by JS as fallback language when no other language could be determined.")
    abstract val defaultLanguage: Property<String>

    @get:Input
    @get:Option(option = "androidSourceFolder", description = "The source folder to generate the localizations class for Android. Defaults to main, but some multiplatform projects use androidMain.")
    abstract val androidSourceFolder: Property<String>

    @get:Input
    @get:Option(option = "iosSourceFolder", description = "The source folder to generate the localizations class for iOS. Defaults to iosArm%Main, but some multiplatform projects use ios%Main. The % symbol will be replaced with 32 or 64. The X64 target for simulator is always iosX64Main.")
    abstract val iosSourceFolder: Property<String>

    @get:Input
    @get:Option(option = "packageName", description = "The package for the generated code")
    abstract val packageName: Property<String>

    @get:Input
    @get:Option(option = "androidStringsPrefix", description = "Prefix which is prepended to the Android generated strings.xml resource files. This is to prevent existing strings.xml from overwriting. Defaults to 'generated_'")
    abstract val androidStringsPrefix: Property<String>

    @get:Input
    @get:Option(option = "srcFolder", description = "The source folder to generate the code to, defaults to 'src'")
    abstract val srcFolder: Property<String>

    @get:Input
    @get:Option(option = "generatedClassName", description = "The name of the generated class containing the expact/actual definitions. Defaults to 'KMMResourcesLocalization.kt'")
    abstract val generatedClassName: Property<String>

    @get:Input
    @get:Option(option = "useDefaultTranslationIfNotInitialized", description = "If true, insert the default translation in the android actual implementation instead of an empry string. This can be useful in unit tests if no android context is available. Defaults to false.")
    abstract val useDefaultTranslationIfNotInitialized: Property<Boolean>

    init {
        description = "Generate localizations task"
    }

    @TaskAction
    fun localize() {
        val generator = LocalizationGenerator(
            input = FileInputStream(input.get()),
            output = output.get(),
            androidApplicationId = androidApplicationId.get(),
            defaultLanguage = defaultLanguage.get(),
            androidSourceFolder = androidSourceFolder.get(),
            iosSourceFolder = iosSourceFolder.get(),
            packageName = packageName.get(),
            androidStringsPrefix = androidStringsPrefix.get(),
            srcFolder = srcFolder.get(),
            generatedClassName = generatedClassName.get(),
            useDefaultTranslationIfNotInitialized = useDefaultTranslationIfNotInitialized.get()
        )
        generator.generate()
    }

    companion object {
        const val NAME = "generateLocalizations"
    }
}
