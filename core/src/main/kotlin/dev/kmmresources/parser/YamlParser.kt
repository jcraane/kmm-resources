package dev.kmmresources.parser

import org.yaml.snakeyaml.Yaml
import java.io.InputStream

class YamlParser(private val inputStream: InputStream) {
    fun parse(): Map<String, Any> {
        val yaml = Yaml()
        val output: Map<String, Any> = yaml.load(inputStream)
        return output
    }
}
