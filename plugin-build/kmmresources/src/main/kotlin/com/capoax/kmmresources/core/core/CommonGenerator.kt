package com.capoax.kmmresources.core;

import com.capoax.kmmresources.backport.onEachIndexed
import com.capoax.kmmresources.extensions.indentation

data class CommonGenerated(
    private val dictionary: Map<String, Any>,
    private val androidRPackage: String,
    private val packageDeclaration: String?,
    private val defaultLanguage: String,
    private val useDefaultTranslationIfNotInitialized: Boolean,

    var generated: String = "// Generated by KMM Resources\n",

    private var generatedExpectFunctions: String = "\n\n",
    private val flattened: MutableList<FlattenedLine> = mutableListOf<FlattenedLine>(),

    val iOSPlatformGenerator: PlatformGenerator = IOSPlatformGenerator(packageDeclaration),
    val androidPlatformGenerator: PlatformGenerator = AndroidPlatformGenerator(
        packageDeclaration = packageDeclaration,
        androidRPackage = androidRPackage,
        useDefaultTranslationIfNotInitialized = useDefaultTranslationIfNotInitialized
    ),
    val jvmPlatformGenerator: PlatformGenerator = JVMPlatformGenerator(packageDeclaration),
    val jsPlatformGenerator: PlatformGenerator = JSPlatformGenerator(packageDeclaration, defaultLanguage)
) {
    val platformGenerators: List<PlatformGenerator>
        get() = listOf(
            iOSPlatformGenerator,
            androidPlatformGenerator,
            jvmPlatformGenerator,
            jsPlatformGenerator
        )

    internal fun generate() {
        if (packageDeclaration != null) {
            generated += packageDeclaration
        }

        generated += generateRoot()
        generated += generatedExpectFunctions

        platformGenerators.forEach(PlatformGenerator::finish)
    }

    private fun generateRoot(): String {
        var generated = """
        |class L {
        |  companion object {
        |
        """.trimMargin()

        var body = ""

        val onEachIndexed = dictionary.toSortedMap().onEachIndexed { index, entry ->
            val childClassName = entry.key.capitalize()
            generated += "${2.indentation}val ${entry.key}: ${childClassName} = ${childClassName}()\n"

            body += generateClass(entry.key, listOf("l"), entry.value)
        }

        generated += "  }\n"
        generated += body
        generated += "}\n"

        return generated
    }

    private fun generateGetOperator(children: Map<*, *>, indentationLevel: Int): String {
        val childrenOfChildren =
            children.mapNotNull { if (it.value?.asStringToStringMap?.let { it.values.all { LocalizationValue(it).numberOfArguments == 0 } } == true) it.key as? String else null }
        return if (childrenOfChildren.isNotEmpty()) {
            val body = childrenOfChildren.map {
                """
            |${indentationLevel.indentation}  "$it" -> ${it}()
            """.trimMargin()
            }.joinToString("\n")
            """
            |${indentationLevel.indentation}operator fun get(key: String): String? = when(key) {
            |$body
            |${indentationLevel.indentation}  else -> null
            |${indentationLevel.indentation}}
            |
            """.trimMargin()
        } else {
            ""
        }
    }

    private fun generateClass(name: String, parentPath: List<String>, rawChildren: Any, indentationLevel: Int = 1): String {
        var children = rawChildren
        if (children is Map<*, *>) {
            children = children.filter { it.value != null }
        } else if (children is List<*>) {
            children = children.map { (it as? Map<*, *>)?.filter { it.value != null } }
        }

        if (children is Map<*, *>) {
            val localizations = children.asStringToStringMap
            if (localizations != null) {
                val id = "${parentPath.joinToString(".")}.$name"
                val numberOfArguments = generate(localizations as Map<String, String>, id)

                val fullNodePath = parentPath.map { it.capitalize() }.joinToString(".")
                val argumentNames = (0 until numberOfArguments).map { "value${it}" }
                val arguments = argumentNames.map { "$it: String" }.joinToString(", ")
                val function = "${fullNodePath}.${name}(${arguments})"

                generatedExpectFunctions += "expect fun ${function}: String\n"
                platformGenerators.forEach { platformGenerator ->
                    platformGenerator.generateActual(function, parentPath, name, numberOfArguments, createDefaultTranslation(children, argumentNames))
                }

                return ""
            } else {
                val children = children as Map<String, Any>
                val className = name.capitalize()
                val childClasses =
                    children.filter { !(it.value.isStringToStringMap || (it.value as? List<*>)?.all { (it as Any).isStringToStringMap } == true) }
                var generated = indentationLevel.indentation
                var body = ""
                val path = parentPath + listOf(name)
                val childIndentationLevel = indentationLevel + 1

                children.toSortedMap().onEachIndexed { _, entry ->
                    body += generateClass(entry.key, path, entry.value, childIndentationLevel)
                }
                body += generateGetOperator(children, childIndentationLevel)

                if (childClasses.isEmpty()) {
                    generated += "class ${className} {\n"
                } else {
                    generated += "data class ${className}(\n"

                    childClasses.toSortedMap().onEachIndexed { index, entry ->
                        val childClassName = entry.key.capitalize()
                        if (index > 0) {
                            generated += ",\n"
                        }
                        val value = entry.value
                        if (value is List<*>) {
                            body += "${childIndentationLevel.indentation}data class ${childClassName}(val index: Int)\n"
                            generated += "${childIndentationLevel.indentation}val ${entry.key}: List<${childClassName}> = listOf("
                            value.mapNotNull { it as? Map<*, *> }.forEachIndexed { index, _ ->
                                if (index > 0) {
                                    generated += ", "
                                }
                                generated += "${childClassName}(${index})"
                            }
                            generated += ")\n"
                        } else {
                            generated += "${childIndentationLevel.indentation}val ${entry.key}: ${childClassName} = ${childClassName}()"
                        }
                    }

                    generated += ") {\n"
                }

                generated += body
                generated += "${indentationLevel.indentation}}\n"

                return generated
            }
        } else {
            val children = children as List<*>
            if (children.all { (it as Any).isStringToStringMap }) {
                val fullNodePath = parentPath.map { it.capitalize() }.joinToString(".")

                val function = "${fullNodePath}.${name}()"
                val id = "${parentPath.joinToString(".")}.${name}"

                generatedExpectFunctions += "expect fun ${function}: List<String>\n"

                platformGenerators.forEach {
                    it.generateActualList(
                        function = function,
                        path = parentPath,
                        name = name,
                        values = children as List<Map<String, String>>,
                        defaultLanguage = defaultLanguage
                    )
                }

                children.forEachIndexed { index, localizations ->
                    generate(localizations as Map<String, String>, id, index)
                }

                return ""
            } else {
                val children = children as List<Map<String, *>>
                val className = name.capitalize()
                val fullNodePath = parentPath.map(String::capitalize).joinToString(".")

                var expectActualGenerated = false
                var index = 0
                children.forEach { entry ->
                    entry.forEach { (_, ev) ->
                        if (ev is Map<*, *> && ev.keys.all { it is String }) {
                            val value = ev as Map<String, Any>
                            value.toSortedMap().forEach { function, t ->
                                if (!expectActualGenerated) {
                                    generatedExpectFunctions += "expect fun ${fullNodePath}.${className}.${function}(): String\n"
                                    val defaultTranslation = (t as LinkedHashMap<String,String>).get(defaultLanguage) ?: ""
                                    platformGenerators.forEach {
                                        it.generateActualObjectList(function, parentPath, name, defaultTranslation)
                                    }
                                }
                                val translations = t.asStringToStringMap
                                if (translations != null) {
                                    val localizations = mutableMapOf<String, String>()
                                    val id = (parentPath + listOf("${name}.${index}") + listOf(function)).joinToString(".")
                                    translations.keys.forEach { key ->
                                        localizations[key] = translations[key] ?: ""
                                    }
                                    generate(localizations, id)
                                }
                            }
                            expectActualGenerated = true
                        }
                        index++
                    }
                }
                return ""
            }
        }

        return ""
    }

    private fun createDefaultTranslation(
        children: Map<*, *>,
        argumentNames: List<String>
    ) = if (useDefaultTranslationIfNotInitialized) {
        var translation = children[defaultLanguage] as String
        argumentNames.forEachIndexed { index, argumentName ->
            val argIndex = index + 1
            translation = translation.replace("%$argIndex\$s", "\$$argumentName")
        }
        translation
    } else {
        ""
    }

    private fun generate(localizations: Map<String, String>, id: String, index: Int? = null): Int {
        val key = id + (index?.let { ".$it" } ?: "")
        val flattenedLine = FlattenedLine(key)
        val numberOfArguments = localizations
            .toSortedMap()
            .map {
                val lang = it.key
                val value = it.value
                val localizationValue = LocalizationValue(value)
                val indexString = index?.let { "$it" } ?: ""
                val fullKey = "${id}${indexString}"

                platformGenerators.forEach { it.generateLocalization(fullKey, localizationValue, lang) }

                flattenedLine.translations[lang] = value

                localizationValue.numberOfArguments
            }
            .max()

        flattened.add(flattenedLine)
        return numberOfArguments ?: 0
    }
}

data class CommonGenerator(
    val dictionary: Map<String, Any>,
    val androidRPackage: String,
    val packageName: String?,
    val defaultLanguage: String,
    val useDefaultTranslationIfNotInitialized: Boolean
) {
    fun generate(): CommonGenerated {
        val packageDeclaration = packageName?.let { "package $it\n\n" }
        val generated = CommonGenerated(
            dictionary = dictionary,
            androidRPackage = androidRPackage,
            packageDeclaration = packageDeclaration,
            defaultLanguage = defaultLanguage,
            useDefaultTranslationIfNotInitialized = useDefaultTranslationIfNotInitialized
        )
        generated.generate()
        return generated
    }
}

data class FlattenedLine(
    val key: String,
    val translations: MutableMap<String, String> = mutableMapOf()
)

data class LocalizationValue(val numberOfArguments: Int, val value: String) {
    constructor(value: String) : this(regex.findAll(value).count(), value)

    fun replacedArgumentsValue(replacement: String): String = regex.replace(value, replacement)

    companion object {
        val regex = Regex("[\$][s]")
    }
}

val Any.isStringToStringMap: Boolean
    get() = (this as? Map<*, *>)?.let { it.keys.all { it is String } && it.values.all { it is String } } ?: false

val Any.asStringToStringMap: Map<String, String>?
    get() {
        return if (isStringToStringMap) {
            this as Map<String, String>
        } else null
    }
