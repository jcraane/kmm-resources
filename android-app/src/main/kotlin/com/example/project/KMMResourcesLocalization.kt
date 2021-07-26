package com.example.project

import nl.jcraane.androidapp.R
import android.content.Context

var localizationContext: Context? = null

actual fun L.ExampleContent.Pages.Page1.body(): String = localizationContext?.getString(R.string.l_exampleContent_pages_page1_body) ?: "Body pagina 1"
actual fun L.ExampleContent.Pages.Page1.title(): String = localizationContext?.getString(R.string.l_exampleContent_pages_page1_title) ?: "Titel pagina 1"
actual fun L.ExampleContent.Pages.Page2.body(): String = localizationContext?.getString(R.string.l_exampleContent_pages_page2_body) ?: """- Dit is punt1
- Dit is punt 2
- Dit is punt 3
"""
actual fun L.ExampleContent.Pages.Page2.title(): String = localizationContext?.getString(R.string.l_exampleContent_pages_page2_title) ?: "Body pagina 1"
actual fun L.ExampleContent.Pages.Page3.Body.checkmarks(): List<String> = listOf(
  localizationContext?.getString(R.string.l_exampleContent_pages_page3_body_checkmarks0) ?: "24/7 telefonisch bereikbaar bij storingen",
  localizationContext?.getString(R.string.l_exampleContent_pages_page3_body_checkmarks1) ?: "Voorrijkosten en arbeidsloon inbegrepen"
)
actual fun L.ExampleContent.Pages.Page3.title(): String = localizationContext?.getString(R.string.l_exampleContent_pages_page3_title) ?: "Titel pagina 3"
actual fun L.General.ampersand1(): String = localizationContext?.getString(R.string.l_general_ampersand1) ?: "Brood \u0026 boter"
actual fun L.General.ampersand2(): String = localizationContext?.getString(R.string.l_general_ampersand2) ?: "Brood & boter"
actual fun L.General.ampersand3(): String = localizationContext?.getString(R.string.l_general_ampersand3) ?: "Nota & betalen"
actual fun L.General.basiclist(): List<String> = listOf(
  localizationContext?.getString(R.string.l_general_basiclist0) ?: "optie 1",
  localizationContext?.getString(R.string.l_general_basiclist1) ?: "optie 2"
)
actual fun L.General.Button.ok(): String = localizationContext?.getString(R.string.l_general_button_ok) ?: "OK"
actual fun L.General.Error.invalidField(): String = localizationContext?.getString(R.string.l_general_error_invalidField) ?: "Ongeldige waarde"
actual fun L.General.Error.tryAgainLater(): String = localizationContext?.getString(R.string.l_general_error_tryAgainLater) ?: "Controleer de internetverbinding en probeer het opnieuw."
actual fun L.General.iDontKnow(): String = localizationContext?.getString(R.string.l_general_iDontKnow) ?: "Weet ik niet"
actual fun L.General.no(): String = localizationContext?.getString(R.string.l_general_no) ?: "Nee"
actual fun L.General.withParams(value0: String, value1: String): String = localizationContext?.getString(R.string.l_general_withParams, value0, value1) ?: "Een $value0 tekst met een $value1"
actual fun L.General.yes(): String = localizationContext?.getString(R.string.l_general_yes) ?: "Ja"
actual fun L.Greetings.hello(): String = localizationContext?.getString(R.string.l_greetings_hello) ?: "Hallo vanuit common code"
actual fun L.Listofobjects.Categories.Samplecategory.Content.body(): String {
   val context = localizationContext
   return context?.getString(context.resources.getIdentifier("l.listofobjects.categories.samplecategory.content.$index.body", "string", context.packageName)) ?: "Dit is de body1"
}
actual fun L.Listofobjects.Categories.Samplecategory.Content.title(): String {
   val context = localizationContext
   return context?.getString(context.resources.getIdentifier("l.listofobjects.categories.samplecategory.content.$index.title", "string", context.packageName)) ?: "Dit is de titel1"
}
actual fun L.Listofobjects.Categories.Samplecategory.detailedDescription(): String = localizationContext?.getString(R.string.l_listofobjects_categories_samplecategory_detailedDescription) ?: "Beschrijving van categorie"
actual fun L.Unit.gas(): String = localizationContext?.getString(R.string.l_unit_gas) ?: "m³"
actual fun L.Unit.Power.u3(): String = localizationContext?.getString(R.string.l_unit_power_u3) ?: "kWh"
actual fun L.Unit.Power.u6(): String = localizationContext?.getString(R.string.l_unit_power_u6) ?: "MWh"
actual fun L.Unit.Power.u9(): String = localizationContext?.getString(R.string.l_unit_power_u9) ?: "GWh"
