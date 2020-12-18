package dev.kmmresources.core

import dev.kmmresources.parser.YamlParser
import java.io.File
import java.io.InputStream
import java.nio.file.Files

class LocalizationGenerator(
    val input: InputStream,
    val output: File,
    val androidRPackage: String,
    val androidDefaultLanguage: String = "en",
    val packageName: String?,
    val androidStringsPrefix: String = "generated_") {

    val commonSrc: File get() = {
        val src = output.resolve("src")
        Files.createDirectories(src.toPath())
        src
    }()

    fun generate() {
        val contents = YamlParser(input).parse()
        val commonGenerated = CommonGenerator(contents, androidRPackage, packageName).generate()
        writeCommon(commonGenerated.generated)
        writeAndroid(commonGenerated.androidPlatformGenerator.generatedActual)
        writeAndroidResources(commonGenerated.androidPlatformGenerator.generated)
        writeIOS(commonGenerated.iOSPlatformGenerator.generatedActual)
        writeIOSResources(commonGenerated.iOSPlatformGenerator.generated)
        writeJVM(commonGenerated.jvmPlatformGenerator.generatedActual)
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

    private fun writeAndroid(contents: String) = contents.writeTo()

    private fun writeAndroidResources(contents: Map<String, String>) {
        val commonMainFolder = commonSrc.resolve("main").resolve("res")
        Files.createDirectories(commonMainFolder.toPath())
        contents.forEach { (lang, contents) ->
            val valuesFolderName = if (lang == androidDefaultLanguage) "values" else "values-${lang}"
            val langFolder = commonMainFolder.resolve(valuesFolderName)
            Files.createDirectories(langFolder.toPath())
            val localizationFile = langFolder.resolve("${androidStringsPrefix}strings.xml")
            localizationFile.delete()
            localizationFile.writeText(contents)

            println("Generated ${localizationFile.path}")
        }
    }

    private fun writeJVM(contents: String) = contents.writeTo("jvm")

    private fun String.writeTo(target: String? = null) {
        var mainFolder = commonSrc.resolve(target?.let { "${it}Main" } ?: "main").resolve("kotlin")
        packageName?.split('.')?.forEach { subfolder ->
            mainFolder = mainFolder.resolve(subfolder)
        }
        Files.createDirectories(mainFolder.toPath())
        val localizationFile = mainFolder.resolve("KMMResourcesLocalization.kt")
        localizationFile.delete()
        Files.createFile(localizationFile.toPath())
        localizationFile.writeText(this)

        println("Generated ${localizationFile.path}")
    }

    companion object {
        private val iosTargets = listOf("ios32", "ios64", "iosX64")
    }
}
