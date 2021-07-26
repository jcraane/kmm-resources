package com.capoax.kmmresources.core

import com.capoax.kmmresources.parser.YamlParser
import java.io.File
import java.io.InputStream
import java.nio.file.Files

class LocalizationGenerator(
    val input: InputStream,
    val output: File,
    val androidApplicationId: String,
    val defaultLanguage: String = "en",
    val androidSourceFolder: String = "main",
    val iosSourceFolder: String = "iosMain",
    val packageName: String?,
    val androidStringsPrefix: String = "generated_",
    val srcFolder: String = "src",
    val generatedClassName: String = "KMMResourcesLocalization.kt",
    private val useDefaultTranslationIfNotInitialized: Boolean) {

    val commonSrc: File get() = {
        val src = output.resolve(srcFolder)
        Files.createDirectories(src.toPath())
        src
    }()

    fun generate() {
        val contents = YamlParser(input).parse()
        val commonGenerated = CommonGenerator(contents, androidApplicationId, packageName, defaultLanguage, useDefaultTranslationIfNotInitialized).generate()
        writeCommon(commonGenerated.generated)
        if (commonGenerated.androidPlatformGenerator.generatedActual.writeTo(androidSourceFolder)) {
            writeAndroidResources(commonGenerated.androidPlatformGenerator.generated, androidSourceFolder)
        }
        if (commonGenerated.iOSPlatformGenerator.generatedActual.writeTo(iosSourceFolder)) {
            writeIOSResources(commonGenerated.iOSPlatformGenerator.generated)
        }
        writeJVM(commonGenerated.jvmPlatformGenerator.generatedActual)
        if (writeJS(commonGenerated.jsPlatformGenerator.generatedActual)) {
            writeJSResources(commonGenerated.jsPlatformGenerator.generated)
        }
    }

    private fun writeCommon(contents: String) = contents.writeTo("common")

    private fun writeIOSResources(contents: Map<String, String>) {
        val commonMainFolder = commonSrc.resolve("commonMain").resolve("resources").resolve("ios")
        Files.createDirectories(commonMainFolder.toPath())
        contents.forEach { (lang, contents) ->
            val langFolder = commonMainFolder.resolve("${lang}.lproj")
            Files.createDirectories(langFolder.toPath())
            val localizationFile = langFolder.resolve("Localizable.strings")
            localizationFile.delete()
            localizationFile.writeText(contents)

            println("Generated ${localizationFile.path}")
        }
    }

    private fun writeAndroidResources(contents: Map<String, String>, androidSourceFolder: String) {
        val commonMainFolder = commonSrc.resolve(androidSourceFolder).resolve("res")
        Files.createDirectories(commonMainFolder.toPath())
        contents.forEach { (lang, contents) ->
            val valuesFolderName = if (lang == defaultLanguage) "values" else "values-${lang}"
            val langFolder = commonMainFolder.resolve(valuesFolderName)
            Files.createDirectories(langFolder.toPath())
            val localizationFile = langFolder.resolve("${androidStringsPrefix}strings.xml")
            localizationFile.delete()
            localizationFile.writeText(contents)

            println("Generated ${localizationFile.path}")
        }
    }

    private fun writeJVM(contents: String) = contents.writeTo("jvm")

    private fun writeJS(contents: String) = contents.writeTo("js")

    private fun writeJSResources(contents: Map<String, String>) {
        var mainFolder = commonSrc.resolve("jsMain").resolve("kotlin")
        packageName?.split('.')?.forEach { subfolder ->
            mainFolder = mainFolder.resolve(subfolder)
        }
        Files.createDirectories(mainFolder.toPath())
        contents.forEach { (lang, contents) ->
            val langFile = mainFolder.resolve("KMMResourcesLocalization_${lang}.kt")
            langFile.delete()
            langFile.writeText(contents)

            println("Generated ${langFile.path}")
        }
    }

    private fun String.writeTo(target: String? = null): Boolean {
        val resolvedTarget = when  {
            target == "main" -> ""
            target?.endsWith("Main") == true -> target.replace("Main", "")
            else -> target
        }
        val mainFolder = commonSrc.resolve(resolvedTarget?.let { "${it}Main" } ?: "main")
        if (!mainFolder.exists()) {
            println("Skip generation of $target since $mainFolder does not exist")
            return false
        }
        var mainSourceFolder = mainFolder.resolve("kotlin")
        packageName?.split('.')?.forEach { subfolder ->
            mainSourceFolder = mainSourceFolder.resolve(subfolder)
        }
        Files.createDirectories(mainSourceFolder.toPath())
        val localizationFile = mainSourceFolder.resolve(generatedClassName)
        localizationFile.delete()
        Files.createFile(localizationFile.toPath())
        localizationFile.writeText(this)

        println("Generated ${localizationFile.path}")
        return true
    }
}
