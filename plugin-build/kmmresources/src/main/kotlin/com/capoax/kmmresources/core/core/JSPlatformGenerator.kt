package com.capoax.kmmresources.core

data class JSPlatformGenerator(
        private val packageDeclaration: String?,
        override val generated: MutableMap<String, String> = mutableMapOf(),
        override var generatedActual: String = """
$packageDeclaration
import kotlinx.browser.window

var currentLanguage: String? = null
val fallbackLanguage = "en"

private fun getString(key: String, vararg formatArgs: String): String {
    val browserLanguage = window.navigator.languages.firstOrNull()
    val language = currentLanguage ?: browserLanguage ?: fallbackLanguage
    val languageKey = language.split('-')[0]
    val languageLocalizations = localizations[language] ?: localizations[languageKey] ?: localizations[fallbackLanguage]
    return languageLocalizations?.let {
        var message = it[key]
        formatArgs.forEachIndexed { index, arg ->
            message = message?.replace("%${'$'}{index + 1}\${'$'}@", arg)
        }
        message
    } ?: ""
}

"""
): PlatformGenerator {

    override fun generateLocalization(key: String, value: LocalizationValue, language: String) {
        if (generated[language] == null) {
            generated[language] = """
// Generated ${language} localization file\n
$packageDeclaration
internal fun localizations_$language(): Map<String, String> {
    val localizations = mutableMapOf<String, String>()

"""
        }

        val stringValue = value.replacedArgumentsValue("\\$@")
        var generatedLanguage = generated[language]!!
        if (stringValue.lines().size > 1) {
            generatedLanguage += "\tlocalizations[\"${key}\"] = \"\"\"\n${stringValue.trim()}\n\"\"\"\n"
        } else {
            generatedLanguage += "\tlocalizations[\"${key}\"] = \"${stringValue}\"\n"
        }
        generated[language] = generatedLanguage
    }

    override fun generateActual(function: String, path: List<String>, name: String, numberOfArguments: Int) {
//        generatedActual += "actual fun ${function}: String = \"\"\n"

        val id = id(path, name)
        val varArgs = (0 until numberOfArguments).map { ", value${it}" }.joinToString("")

        generatedActual += "actual fun ${function}: String = getString(\"${id}\"${varArgs})\n"
    }

    override fun generateActualList(function: String, path: List<String>, name: String, values: List<Map<String, String>>) {
        generatedActual += "actual fun ${function}: List<String> = emptyList()\n"
    }

    override fun generateActualObjectList(function: String, path: List<String>, name: String) {
        val id = (path + listOf("${name}.\$index") + listOf(function)).joinToString(".")
        val functionName = "${(path.map { it.capitalize() } + listOf(name.capitalize()) + listOf(function)).joinToString(".")}()"
        generatedActual += "actual fun ${functionName}: String = \"\"\n"
    }

    override fun finish() {
        generatedActual += """
        |
        |val localizations = {
        |    val localizations = mutableMapOf<String, Map<String, String>>()
        |
        """.trimMargin()
        generated.keys.forEach { language ->
            generated[language] += "\treturn localizations\n}\n"
            generatedActual += "\tlocalizations[\"$language\"] = localizations_$language()\n"
        }
        generatedActual += """
        |   localizations
        |}()
        |
        """.trimMargin()
    }

    companion object {
        private fun id(path: List<String>, name: String): String =
                "${path.joinToString(".")}.${name}"
    }
}
