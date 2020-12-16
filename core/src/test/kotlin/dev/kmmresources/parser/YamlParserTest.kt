package dev.kmmresources.parser

import dev.kmmresources.extensions.inputStream
import org.intellij.lang.annotations.Language
import kotlin.test.Test

class YamlParserTest {
    @Test
    fun parseValidYaml() {
        @Language("yaml") val yaml = """
            general:
              button:
                ok:
                  nl: "OK"
                  en: "OK"
        """.trimIndent()

        val output = YamlParser(yaml.inputStream).parse()
        println(output)
    }
}
