package com.example.project

import nl.jcraane.androidapp.R
import android.content.Context

var localizationContext: Context? = null

actual fun L.BoilerComfort.Check.title(): String = localizationContext?.getString(R.string.l_boilerComfort_check_title) ?: ""
actual fun L.BoilerComfort.ChooseSubscription.checkmarks(): List<String> = listOf(
  localizationContext?.getString(R.string.l_boilerComfort_chooseSubscription_checkmarks0) ?: "",
  localizationContext?.getString(R.string.l_boilerComfort_chooseSubscription_checkmarks1) ?: ""
)
actual fun L.BoilerComfort.ChooseSubscription.MaintenanceInterval.header(): String = localizationContext?.getString(R.string.l_boilerComfort_chooseSubscription_maintenanceInterval_header) ?: ""
actual fun L.BoilerComfort.ChooseSubscription.MaterialCosts.header(): String = localizationContext?.getString(R.string.l_boilerComfort_chooseSubscription_materialCosts_header) ?: ""
actual fun L.BoilerComfort.Pages.Asbestos.Questions.Asbestos.body(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_asbestos_questions_asbestos_body) ?: ""
actual fun L.BoilerComfort.Pages.Asbestos.Questions.Asbestos.Dialog.message(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_asbestos_questions_asbestos_dialog_message) ?: ""
actual fun L.BoilerComfort.Pages.Asbestos.Questions.Asbestos.Dialog.title(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_asbestos_questions_asbestos_dialog_title) ?: ""
actual fun L.BoilerComfort.Pages.Asbestos.Questions.Asbestos.Doubts.Dialog.message(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_asbestos_questions_asbestos_doubts_dialog_message) ?: ""
actual fun L.BoilerComfort.Pages.Asbestos.Questions.Asbestos.Doubts.Dialog.title(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_asbestos_questions_asbestos_doubts_dialog_title) ?: ""
actual fun L.BoilerComfort.Pages.Asbestos.Questions.Asbestos.Doubts.title(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_asbestos_questions_asbestos_doubts_title) ?: ""
actual fun L.BoilerComfort.Pages.Asbestos.Questions.Asbestos.title(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_asbestos_questions_asbestos_title) ?: ""
actual fun L.BoilerComfort.Pages.BoilerAccessibile.Questions.Ladder.Dialog.message(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_boilerAccessibile_questions_ladder_dialog_message) ?: ""
actual fun L.BoilerComfort.Pages.BoilerAccessibile.Questions.Ladder.Dialog.title(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_boilerAccessibile_questions_ladder_dialog_title) ?: ""
actual fun L.BoilerComfort.Pages.BoilerAccessibile.Questions.Ladder.title(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_boilerAccessibile_questions_ladder_title) ?: ""
actual fun L.BoilerComfort.Pages.BoilerAccessibile.Questions.Space.Dialog.message(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_boilerAccessibile_questions_space_dialog_message) ?: ""
actual fun L.BoilerComfort.Pages.BoilerAccessibile.Questions.Space.Dialog.title(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_boilerAccessibile_questions_space_dialog_title) ?: ""
actual fun L.BoilerComfort.Pages.BoilerAccessibile.Questions.Space.title(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_boilerAccessibile_questions_space_title) ?: ""
actual fun L.BoilerComfort.Pages.BoilerAccessibile.Questions.Strairwell.Dialog.message(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_boilerAccessibile_questions_strairwell_dialog_message) ?: ""
actual fun L.BoilerComfort.Pages.BoilerAccessibile.Questions.Strairwell.Dialog.title(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_boilerAccessibile_questions_strairwell_dialog_title) ?: ""
actual fun L.BoilerComfort.Pages.BoilerAccessibile.Questions.Strairwell.title(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_boilerAccessibile_questions_strairwell_title) ?: ""
actual fun L.BoilerComfort.Pages.BoilerAge.Questions.BoilerAge.Answers.olderThan10(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_boilerAge_questions_boilerAge_answers_olderThan10) ?: ""
actual fun L.BoilerComfort.Pages.BoilerAge.Questions.BoilerAge.Answers.youngerThan10(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_boilerAge_questions_boilerAge_answers_youngerThan10) ?: ""
actual fun L.BoilerComfort.Pages.BoilerAge.Questions.BoilerAge.body(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_boilerAge_questions_boilerAge_body) ?: ""
actual fun L.BoilerComfort.Pages.BoilerAge.Questions.BoilerAge.title(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_boilerAge_questions_boilerAge_title) ?: ""
actual fun L.BoilerComfort.Pages.BoilerBrand.Questions.BoilerBrand.body(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_boilerBrand_questions_boilerBrand_body) ?: ""
actual fun L.BoilerComfort.Pages.BoilerBrand.Questions.BoilerBrand.Dialogs.DontKnowBrand.message(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_boilerBrand_questions_boilerBrand_dialogs_dontKnowBrand_message) ?: ""
actual fun L.BoilerComfort.Pages.BoilerBrand.Questions.BoilerBrand.Dialogs.DontKnowBrand.title(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_boilerBrand_questions_boilerBrand_dialogs_dontKnowBrand_title) ?: ""
actual fun L.BoilerComfort.Pages.BoilerBrand.Questions.BoilerBrand.Dialogs.NotListed.message(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_boilerBrand_questions_boilerBrand_dialogs_notListed_message) ?: ""
actual fun L.BoilerComfort.Pages.BoilerBrand.Questions.BoilerBrand.Dialogs.NotListed.title(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_boilerBrand_questions_boilerBrand_dialogs_notListed_title) ?: ""
actual fun L.BoilerComfort.Pages.BoilerBrand.Questions.BoilerBrand.title(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_boilerBrand_questions_boilerBrand_title) ?: ""
actual fun L.BoilerComfort.Pages.BoilerType.Questions.BoilerType.Dialog.message(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_boilerType_questions_boilerType_dialog_message) ?: ""
actual fun L.BoilerComfort.Pages.BoilerType.Questions.BoilerType.Dialog.title(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_boilerType_questions_boilerType_dialog_title) ?: ""
actual fun L.BoilerComfort.Pages.BoilerType.Questions.BoilerType.title(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_boilerType_questions_boilerType_title) ?: ""
actual fun L.BoilerComfort.Pages.HasOwnBoiler.body(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_hasOwnBoiler_body) ?: ""
actual fun L.BoilerComfort.Pages.HasOwnBoiler.Questions.HasBoiler.Dialog.message(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_hasOwnBoiler_questions_hasBoiler_dialog_message) ?: ""
actual fun L.BoilerComfort.Pages.HasOwnBoiler.Questions.HasBoiler.Dialog.title(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_hasOwnBoiler_questions_hasBoiler_dialog_title) ?: ""
actual fun L.BoilerComfort.Pages.HasOwnBoiler.Questions.HasBoiler.title(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_hasOwnBoiler_questions_hasBoiler_title) ?: ""
actual fun L.BoilerComfort.Pages.HasOwnBoiler.Questions.OwnBoiler.Dialog.message(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_hasOwnBoiler_questions_ownBoiler_dialog_message) ?: ""
actual fun L.BoilerComfort.Pages.HasOwnBoiler.Questions.OwnBoiler.Dialog.title(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_hasOwnBoiler_questions_ownBoiler_dialog_title) ?: ""
actual fun L.BoilerComfort.Pages.HasOwnBoiler.Questions.OwnBoiler.title(): String = localizationContext?.getString(R.string.l_boilerComfort_pages_hasOwnBoiler_questions_ownBoiler_title) ?: ""
actual fun L.Breakdown.Categories.Standby.Content.body(): String {
   val context = localizationContext
   return context?.getString(context.resources.getIdentifier("l.breakdown.categories.standby.content.$index.body", "string", context.packageName)) ?: ""
}
actual fun L.Breakdown.Categories.Standby.Content.title(): String {
   val context = localizationContext
   return context?.getString(context.resources.getIdentifier("l.breakdown.categories.standby.content.$index.title", "string", context.packageName)) ?: ""
}
actual fun L.Breakdown.Categories.Standby.detailedDescription(): String = localizationContext?.getString(R.string.l_breakdown_categories_standby_detailedDescription) ?: ""
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
actual fun L.Unit.Electricity.u3(): String = localizationContext?.getString(R.string.l_unit_electricity_u3) ?: ""
actual fun L.Unit.Electricity.u6(): String = localizationContext?.getString(R.string.l_unit_electricity_u6) ?: ""
actual fun L.Unit.Electricity.u9(): String = localizationContext?.getString(R.string.l_unit_electricity_u9) ?: ""
actual fun L.Unit.gas(): String = localizationContext?.getString(R.string.l_unit_gas) ?: ""
