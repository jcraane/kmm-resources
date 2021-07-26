package com.capoax.kmmresources.core

data class IOSPlatformGenerator(
        private val packageDeclaration: String?,
        override val generated: MutableMap<String, String> = mutableMapOf(),
        override var generatedActual: String = """
        |$packageDeclaration
        |import platform.Foundation.NSBundle
        |import platform.Foundation.NSString
        |import platform.Foundation.localizedStringWithFormat
        |
        |var localizationBundle = NSBundle.mainBundle()
        |
        |
        """.trimMargin()
): PlatformGenerator {

    override fun generateLocalization(key: String, value: LocalizationValue, language: String) {
        if (generated[language] == null) {
            generated[language] = "// Generated ${language} localization file\n"
        }

        val stringValue = value.replacedArgumentsValue("\\$@")
        var generatedLanguage = generated[language]!!
        generatedLanguage += "\"${key}\" = \"${stringValue}\";\n"
        generated[language] = generatedLanguage
    }

    override fun generateActual(function: String, path: List<String>, name: String, numberOfArguments: Int, defaultTranslation: String) {
        val id = id(path, name)
        val varArgs = (0 until numberOfArguments).map { ", value${it} as NSString" }.joinToString("")

        generatedActual +=
                """
        |actual fun ${function}: String {
        |    val localizedString = localizationBundle.localizedStringForKey("${id}", null, null)
        |    return NSString.localizedStringWithFormat(localizedString${varArgs})
        |}
        |
        """.trimMargin()
    }

    override fun generateActualList(function: String, path: List<String>, name: String, values: List<Map<String, String>>, defaultLanguage: String) {
        val id = id(path, name)
        generatedActual += "actual fun ${function}: List<String> = listOf(\n"

        values.forEachIndexed { index, _ ->
            if (index > 0) {
                generatedActual += ",\n"
            }
            generatedActual += "  localizationBundle.localizedStringForKey(\"${id}${index}\", null, null)"
        }
        generatedActual += "\n)\n"
    }

    override fun generateActualObjectList(function: String, path: List<String>, name: String, defaultTranslation: String) {
        val id = (path + listOf("${name}.\$index") + listOf(function)).joinToString(".")
        val functionName = "${(path.map { it.capitalize() } + listOf(name.capitalize()) + listOf(function)).joinToString(".")}()"
        generatedActual +=
                """
                |actual fun ${functionName}: String {
                |    val localizedString = localizationBundle.localizedStringForKey("${id}", null, null)
                |    return NSString.localizedStringWithFormat(localizedString)
                |}
                |
                """.trimMargin()
    }

    override fun finish() {
    }

    companion object {
        private fun id(path: List<String>, name: String): String =
                "${path.joinToString(".")}.${name}"
    }
}
