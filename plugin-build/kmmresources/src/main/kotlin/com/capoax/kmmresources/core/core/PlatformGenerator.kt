package com.capoax.kmmresources.core

interface PlatformGenerator {
    val generated: Map<String, String>
    val generatedActual: String

    fun generateLocalization(key: String, value: LocalizationValue, language: String)
    fun generateActual(function: String, path: List<String>, name: String, numberOfArguments: Int, defaultTranslation: String = "")
    fun generateActualList(function: String, path: List<String>, name: String, values: List<Map<String, String>>, defaultLanguage: String)
    fun generateActualObjectList(function: String, path: List<String>, name: String, defaultTranslation: String = "")
    fun finish()
}
