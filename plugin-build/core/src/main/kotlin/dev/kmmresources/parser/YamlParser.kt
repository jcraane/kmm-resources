package dev.kmmresources.parser

import org.yaml.snakeyaml.DumperOptions
import org.yaml.snakeyaml.LoaderOptions
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import org.yaml.snakeyaml.nodes.Tag
import org.yaml.snakeyaml.representer.Representer
import org.yaml.snakeyaml.resolver.Resolver
import java.io.InputStream

class YamlParser(private val inputStream: InputStream) {
    fun parse(): Map<String, Any> {
        val yaml = Yaml(Constructor(), Representer(), DumperOptions(), LoaderOptions(), CustomResolver())
        val output: Map<String, Any> = yaml.load(inputStream)
        return output
    }
}

class CustomResolver: Resolver() {
    override fun addImplicitResolvers() {
        // overwriting to remove BOOL, INT and NULL

        addImplicitResolver(Tag.FLOAT, FLOAT, "-+0123456789.")
        addImplicitResolver(Tag.MERGE, MERGE, "<")
        addImplicitResolver(Tag.TIMESTAMP, TIMESTAMP, "0123456789")
        // The following implicit resolver is only for documentation
        // purposes.
        // It cannot work
        // because plain scalars cannot start with '!', '&', or '*'.
        // The following implicit resolver is only for documentation
        // purposes.
        // It cannot work
        // because plain scalars cannot start with '!', '&', or '*'.
        addImplicitResolver(Tag.YAML, YAML, "!&*")
    }
}
