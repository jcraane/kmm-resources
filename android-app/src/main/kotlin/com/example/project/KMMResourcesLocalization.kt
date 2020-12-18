package com.example.project

import nl.jcraane.androidapp.R
import android.content.Context

var localizationContext: Context? = null

actual fun L.ExampleContent.Pages.Page1.body(): String = localizationContext?.getString(R.string.l_exampleContent_pages_page1_body) ?: ""
actual fun L.ExampleContent.Pages.Page1.title(): String = localizationContext?.getString(R.string.l_exampleContent_pages_page1_title) ?: ""
actual fun L.ExampleContent.Pages.Page2.body(): String = localizationContext?.getString(R.string.l_exampleContent_pages_page2_body) ?: ""
actual fun L.ExampleContent.Pages.Page2.title(): String = localizationContext?.getString(R.string.l_exampleContent_pages_page2_title) ?: ""
actual fun L.ExampleContent.Pages.Page3.Body.checkmarks(): List<String> = listOf(
  localizationContext?.getString(R.string.l_exampleContent_pages_page3_body_checkmarks0) ?: "",
  localizationContext?.getString(R.string.l_exampleContent_pages_page3_body_checkmarks1) ?: ""
)
actual fun L.ExampleContent.Pages.Page3.title(): String = localizationContext?.getString(R.string.l_exampleContent_pages_page3_title) ?: ""
actual fun L.General.ampersand1(): String = localizationContext?.getString(R.string.l_general_ampersand1) ?: ""
actual fun L.General.ampersand2(): String = localizationContext?.getString(R.string.l_general_ampersand2) ?: ""
actual fun L.General.ampersand3(): String = localizationContext?.getString(R.string.l_general_ampersand3) ?: ""
actual fun L.General.Button.ok(): String = localizationContext?.getString(R.string.l_general_button_ok) ?: ""
actual fun L.General.Error.invalidField(): String = localizationContext?.getString(R.string.l_general_error_invalidField) ?: ""
actual fun L.General.Error.tryAgainLater(): String = localizationContext?.getString(R.string.l_general_error_tryAgainLater) ?: ""
actual fun L.General.iDontKnow(): String = localizationContext?.getString(R.string.l_general_iDontKnow) ?: ""
actual fun L.General.no(): String = localizationContext?.getString(R.string.l_general_no) ?: ""
actual fun L.General.withParams(value0: String, value1: String): String = localizationContext?.getString(R.string.l_general_withParams, value0, value1) ?: ""
actual fun L.General.yes(): String = localizationContext?.getString(R.string.l_general_yes) ?: ""
actual fun L.Greetings.hello(): String = localizationContext?.getString(R.string.l_greetings_hello) ?: ""
actual fun L.Listofobjects.Categories.Samplecategory.Content.body(): String {
   val context = localizationContext
   return context?.getString(context.resources.getIdentifier("l.listofobjects.categories.samplecategory.content.$index.body", "string", context.packageName)) ?: ""
}
actual fun L.Listofobjects.Categories.Samplecategory.Content.title(): String {
   val context = localizationContext
   return context?.getString(context.resources.getIdentifier("l.listofobjects.categories.samplecategory.content.$index.title", "string", context.packageName)) ?: ""
}
actual fun L.Listofobjects.Categories.Samplecategory.detailedDescription(): String = localizationContext?.getString(R.string.l_listofobjects_categories_samplecategory_detailedDescription) ?: ""
actual fun L.Unit.gas(): String = localizationContext?.getString(R.string.l_unit_gas) ?: ""
actual fun L.Unit.Power.u3(): String = localizationContext?.getString(R.string.l_unit_power_u3) ?: ""
actual fun L.Unit.Power.u6(): String = localizationContext?.getString(R.string.l_unit_power_u6) ?: ""
actual fun L.Unit.Power.u9(): String = localizationContext?.getString(R.string.l_unit_power_u9) ?: ""
