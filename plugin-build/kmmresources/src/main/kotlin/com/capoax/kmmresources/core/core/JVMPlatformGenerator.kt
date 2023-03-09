package com.capoax.kmmresources.core

import com.capoax.kmmresources.extensions.isMultiline

data class JVMPlatformGenerator(
        private val packageDeclaration: String?,
        override val generated: MutableMap<String, String> = mutableMapOf(),
        override var generatedActual: String = "$packageDeclaration"
): PlatformGenerator {

    override fun generateLocalization(key: String, value: LocalizationValue, language: String) {
    }

    override fun generateActual(function: String, path: List<String>, name: String, numberOfArguments: Int, defaultTranslation: String) {
        val stringDelimeter = getStringDelimeter(defaultTranslation)
        generatedActual += "actual fun ${function}: String = $stringDelimeter$defaultTranslation$stringDelimeter\n"
    }

    private fun getStringDelimeter(defaultTranslation: String) = if (defaultTranslation.isMultiline()) "\"\"\"" else "\""

    override fun generateActualList(function: String, path: List<String>, name: String, values: List<Map<String, String>>, defaultLanguage: String) {
        generatedActual += "actual fun ${function}: List<String> = emptyList()\n"
    }

    override fun generateActualObjectList(function: String, path: List<String>, name: String, defaultTranslation: String) {
        val id = (path + listOf("${name}.\$index") + listOf(function)).joinToString(".")
        val functionName = "${(path.map { it.capitalize() } + listOf(name.capitalize()) + listOf(function)).joinToString(".")}()"
        generatedActual += "actual fun ${functionName}: String = \"\"\n"
    }

    override fun finish() {
    }
}
