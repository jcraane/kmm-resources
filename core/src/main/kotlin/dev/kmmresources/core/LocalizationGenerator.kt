package dev.kmmresources.core

import java.io.File
import java.nio.file.Files

class LocalizationGenerator(
    input: File,
    val output: File,
    androidRPackage: String,
    packageName: String?,
    val sharedModuleFolder: String? = null) {

    val commonSrc: File get() = {
        val src = output.resolve(sharedModuleFolder ?: "shared").resolve("src").resolve("create_from_plugin")
        Files.createDirectories(src.toPath())
        src
    }()

    fun generate() {

    }
}
