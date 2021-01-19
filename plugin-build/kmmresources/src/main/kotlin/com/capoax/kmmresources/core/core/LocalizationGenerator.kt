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
    val iosSourceFolder: String = "iosArm%Main",
    val packageName: String?,
    val androidStringsPrefix: String = "generated_",
    val srcFolder: String = "src",
    val generatedClassName: String = "KMMResourcesLocalization.kt") {

    val commonSrc: File get() = {
        val src = output.resolve(srcFolder)
        Files.createDirectories(src.toPath())
        src
    }()


    private val iosTargets: List<String> = {
        val targets = mutableListOf("iosX64")
        targets += listOf(32, 64).map { iosSourceFolder.replace("%", it.toString()) }
        targets
    }()

    fun generate() {
        val contents = YamlParser(input).parse()
        val commonGenerated = CommonGenerator(contents, androidApplicationId, packageName, defaultLanguage).generate()
        writeCommon(commonGenerated.generated)
        writeAndroid(commonGenerated.androidPlatformGenerator.generatedActual, androidSourceFolder)
        writeAndroidResources(commonGenerated.androidPlatformGenerator.generated, androidSourceFolder)
        writeIOS(commonGenerated.iOSPlatformGenerator.generatedActual)
        writeIOSResources(commonGenerated.iOSPlatformGenerator.generated)
        writeJVM(commonGenerated.jvmPlatformGenerator.generatedActual)
        writeJS(commonGenerated.jsPlatformGenerator.generatedActual)
        writeJSResources(commonGenerated.jsPlatformGenerator.generated)
    }

    private fun writeCommon(contents: String) = contents.writeTo("common")

    private fun writeIOS(contents: String) = iosTargets.forEach { target ->
        contents.writeTo(target)
    }

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

    private fun writeAndroid(contents: String, target: String) = contents.writeTo(target)

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

    private fun String.writeTo(target: String? = null) {
        val resolvedTarget = when  {
            target == "main" -> ""
            target?.endsWith("Main") == true -> target.replace("Main", "")
            else -> target
        }
        var mainFolder = commonSrc.resolve(resolvedTarget?.let { "${it}Main" } ?: "main").resolve("kotlin")
        packageName?.split('.')?.forEach { subfolder ->
            mainFolder = mainFolder.resolve(subfolder)
        }
        Files.createDirectories(mainFolder.toPath())
        val localizationFile = mainFolder.resolve(generatedClassName)
        localizationFile.delete()
        Files.createFile(localizationFile.toPath())
        localizationFile.writeText(this)

        println("Generated ${localizationFile.path}")
    }
}
