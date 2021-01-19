
package com.example.project


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
            message = message?.replace("%${index + 1}\$@", arg)
        }
        message
    } ?: ""
}

actual fun L.ExampleContent.Pages.Page1.body(): String = getString("l.exampleContent.pages.page1.body")
actual fun L.ExampleContent.Pages.Page1.title(): String = getString("l.exampleContent.pages.page1.title")
actual fun L.ExampleContent.Pages.Page2.body(): String = getString("l.exampleContent.pages.page2.body")
actual fun L.ExampleContent.Pages.Page2.title(): String = getString("l.exampleContent.pages.page2.title")
actual fun L.ExampleContent.Pages.Page3.Body.checkmarks(): List<String> = listOf(
  getString("l.exampleContent.pages.page3.body.checkmarks0"),
  getString("l.exampleContent.pages.page3.body.checkmarks1")
)
actual fun L.ExampleContent.Pages.Page3.title(): String = getString("l.exampleContent.pages.page3.title")
actual fun L.General.ampersand1(): String = getString("l.general.ampersand1")
actual fun L.General.ampersand2(): String = getString("l.general.ampersand2")
actual fun L.General.ampersand3(): String = getString("l.general.ampersand3")
actual fun L.General.Button.ok(): String = getString("l.general.button.ok")
actual fun L.General.Error.invalidField(): String = getString("l.general.error.invalidField")
actual fun L.General.Error.tryAgainLater(): String = getString("l.general.error.tryAgainLater")
actual fun L.General.iDontKnow(): String = getString("l.general.iDontKnow")
actual fun L.General.no(): String = getString("l.general.no")
actual fun L.General.withParams(value0: String, value1: String): String = getString("l.general.withParams", value0, value1)
actual fun L.General.yes(): String = getString("l.general.yes")
actual fun L.Greetings.hello(): String = getString("l.greetings.hello")
actual fun L.Listofobjects.Categories.Samplecategory.Content.body(): String = ""
actual fun L.Listofobjects.Categories.Samplecategory.Content.title(): String = ""
actual fun L.Listofobjects.Categories.Samplecategory.detailedDescription(): String = getString("l.listofobjects.categories.samplecategory.detailedDescription")
actual fun L.Unit.gas(): String = getString("l.unit.gas")
actual fun L.Unit.Power.u3(): String = getString("l.unit.power.u3")
actual fun L.Unit.Power.u6(): String = getString("l.unit.power.u6")
actual fun L.Unit.Power.u9(): String = getString("l.unit.power.u9")

val localizations = {
    val localizations = mutableMapOf<String, Map<String, String>>()
	localizations["en"] = localizations_en()
	localizations["nl"] = localizations_nl()
   localizations
}()
