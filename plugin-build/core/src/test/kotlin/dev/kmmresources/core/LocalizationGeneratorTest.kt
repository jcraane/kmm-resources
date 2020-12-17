package dev.kmmresources.core

import dev.kmmresources.extensions.inputStream
import org.intellij.lang.annotations.Language
import org.junit.Test
import java.io.File

class LocalizationGeneratorTest {

    @Test
    fun runGenerator() {

//        @Language("yaml") val yaml = """
//            general:
//              button:
//                ok:
//                  nl: "OK"
//                  en: "OK"
//        """.trimIndent()

        val input = LocalizationGeneratorTest::class.java.classLoader.getResourceAsStream("generic.yaml")
        val output = File("build").resolve("test_output")
        output.deleteRecursively()
        val generator = LocalizationGenerator(input, output, "com.eneco.enecolib", "nl", "com.eneco.enecoapp.shared.common.localization", "common")
        generator.generate()
    }
}
