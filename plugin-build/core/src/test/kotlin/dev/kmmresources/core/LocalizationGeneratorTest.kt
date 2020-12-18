package dev.kmmresources.core

import com.capoax.kmmresources.core.LocalizationGenerator
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
        val generator = LocalizationGenerator(
            input = input,
            output = output,
            androidApplicationId = "com.example",
            androidDefaultLanguage = "nl",
            packageName = "com.example.common.localization"
        )
        generator.generate()
    }
}
