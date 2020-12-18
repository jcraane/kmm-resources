import platform.Foundation.NSBundle
import platform.Foundation.NSString
import platform.Foundation.localizedStringWithFormat

var localizationBundle = NSBundle.mainBundle()

actual fun L.BoilerComfort.Check.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.check.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.ChooseSubscription.checkmarks(): List<String> = listOf(
  localizationBundle.localizedStringForKey("l.boilerComfort.chooseSubscription.checkmarks0", null, null),
  localizationBundle.localizedStringForKey("l.boilerComfort.chooseSubscription.checkmarks1", null, null)
)
actual fun L.BoilerComfort.ChooseSubscription.MaintenanceInterval.header(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.chooseSubscription.maintenanceInterval.header", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.ChooseSubscription.MaterialCosts.header(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.chooseSubscription.materialCosts.header", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.Asbestos.Questions.Asbestos.body(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.asbestos.questions.asbestos.body", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.Asbestos.Questions.Asbestos.Dialog.message(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.asbestos.questions.asbestos.dialog.message", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.Asbestos.Questions.Asbestos.Dialog.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.asbestos.questions.asbestos.dialog.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.Asbestos.Questions.Asbestos.Doubts.Dialog.message(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.asbestos.questions.asbestos.doubts.dialog.message", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.Asbestos.Questions.Asbestos.Doubts.Dialog.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.asbestos.questions.asbestos.doubts.dialog.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.Asbestos.Questions.Asbestos.Doubts.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.asbestos.questions.asbestos.doubts.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.Asbestos.Questions.Asbestos.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.asbestos.questions.asbestos.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.BoilerAccessibile.Questions.Ladder.Dialog.message(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.boilerAccessibile.questions.ladder.dialog.message", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.BoilerAccessibile.Questions.Ladder.Dialog.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.boilerAccessibile.questions.ladder.dialog.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.BoilerAccessibile.Questions.Ladder.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.boilerAccessibile.questions.ladder.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.BoilerAccessibile.Questions.Space.Dialog.message(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.boilerAccessibile.questions.space.dialog.message", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.BoilerAccessibile.Questions.Space.Dialog.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.boilerAccessibile.questions.space.dialog.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.BoilerAccessibile.Questions.Space.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.boilerAccessibile.questions.space.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.BoilerAccessibile.Questions.Strairwell.Dialog.message(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.boilerAccessibile.questions.strairwell.dialog.message", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.BoilerAccessibile.Questions.Strairwell.Dialog.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.boilerAccessibile.questions.strairwell.dialog.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.BoilerAccessibile.Questions.Strairwell.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.boilerAccessibile.questions.strairwell.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.BoilerAge.Questions.BoilerAge.Answers.olderThan10(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.boilerAge.questions.boilerAge.answers.olderThan10", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.BoilerAge.Questions.BoilerAge.Answers.youngerThan10(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.boilerAge.questions.boilerAge.answers.youngerThan10", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.BoilerAge.Questions.BoilerAge.body(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.boilerAge.questions.boilerAge.body", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.BoilerAge.Questions.BoilerAge.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.boilerAge.questions.boilerAge.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.BoilerBrand.Questions.BoilerBrand.body(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.boilerBrand.questions.boilerBrand.body", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.BoilerBrand.Questions.BoilerBrand.Dialogs.DontKnowBrand.message(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.boilerBrand.questions.boilerBrand.dialogs.dontKnowBrand.message", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.BoilerBrand.Questions.BoilerBrand.Dialogs.DontKnowBrand.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.boilerBrand.questions.boilerBrand.dialogs.dontKnowBrand.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.BoilerBrand.Questions.BoilerBrand.Dialogs.NotListed.message(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.boilerBrand.questions.boilerBrand.dialogs.notListed.message", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.BoilerBrand.Questions.BoilerBrand.Dialogs.NotListed.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.boilerBrand.questions.boilerBrand.dialogs.notListed.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.BoilerBrand.Questions.BoilerBrand.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.boilerBrand.questions.boilerBrand.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.BoilerType.Questions.BoilerType.Dialog.message(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.boilerType.questions.boilerType.dialog.message", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.BoilerType.Questions.BoilerType.Dialog.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.boilerType.questions.boilerType.dialog.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.BoilerType.Questions.BoilerType.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.boilerType.questions.boilerType.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.HasOwnBoiler.body(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.hasOwnBoiler.body", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.HasOwnBoiler.Questions.HasBoiler.Dialog.message(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.hasOwnBoiler.questions.hasBoiler.dialog.message", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.HasOwnBoiler.Questions.HasBoiler.Dialog.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.hasOwnBoiler.questions.hasBoiler.dialog.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.HasOwnBoiler.Questions.HasBoiler.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.hasOwnBoiler.questions.hasBoiler.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.HasOwnBoiler.Questions.OwnBoiler.Dialog.message(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.hasOwnBoiler.questions.ownBoiler.dialog.message", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.HasOwnBoiler.Questions.OwnBoiler.Dialog.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.hasOwnBoiler.questions.ownBoiler.dialog.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.BoilerComfort.Pages.HasOwnBoiler.Questions.OwnBoiler.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.boilerComfort.pages.hasOwnBoiler.questions.ownBoiler.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.Breakdown.Categories.Standby.Content.body(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.breakdown.categories.standby.content.$index.body", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.Breakdown.Categories.Standby.Content.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.breakdown.categories.standby.content.$index.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.Breakdown.Categories.Standby.detailedDescription(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.breakdown.categories.standby.detailedDescription", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.General.ampersand1(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.general.ampersand1", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.General.ampersand2(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.general.ampersand2", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.General.ampersand3(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.general.ampersand3", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.General.Button.ok(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.general.button.ok", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.General.Error.invalidField(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.general.error.invalidField", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.General.Error.tryAgainLater(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.general.error.tryAgainLater", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.General.iDontKnow(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.general.iDontKnow", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.General.no(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.general.no", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.General.withParams(value0: String, value1: String): String {
    val localizedString = localizationBundle.localizedStringForKey("l.general.withParams", null, null)
    return NSString.localizedStringWithFormat(localizedString, value0 as NSString, value1 as NSString)
}
actual fun L.General.yes(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.general.yes", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.Greetings.hello(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.greetings.hello", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.Unit.Electricity.u3(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.unit.electricity.u3", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.Unit.Electricity.u6(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.unit.electricity.u6", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.Unit.Electricity.u9(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.unit.electricity.u9", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.Unit.gas(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.unit.gas", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
