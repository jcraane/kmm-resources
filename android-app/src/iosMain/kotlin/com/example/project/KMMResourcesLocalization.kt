package com.example.project


import platform.Foundation.NSBundle
import platform.Foundation.NSString
import platform.Foundation.localizedStringWithFormat

var localizationBundle = NSBundle.mainBundle()

actual fun L.ExampleContent.Pages.Page1.body(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.exampleContent.pages.page1.body", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.ExampleContent.Pages.Page1.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.exampleContent.pages.page1.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.ExampleContent.Pages.Page2.body(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.exampleContent.pages.page2.body", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.ExampleContent.Pages.Page2.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.exampleContent.pages.page2.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.ExampleContent.Pages.Page3.Body.checkmarks(): List<String> = listOf(
  localizationBundle.localizedStringForKey("l.exampleContent.pages.page3.body.checkmarks0", null, null),
  localizationBundle.localizedStringForKey("l.exampleContent.pages.page3.body.checkmarks1", null, null)
)
actual fun L.ExampleContent.Pages.Page3.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.exampleContent.pages.page3.title", null, null)
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
actual fun L.General.basiclist(): List<String> = listOf(
  localizationBundle.localizedStringForKey("l.general.basiclist0", null, null),
  localizationBundle.localizedStringForKey("l.general.basiclist1", null, null)
)
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
actual fun L.Listofobjects.Categories.Samplecategory.Content.body(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.listofobjects.categories.samplecategory.content.$index.body", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.Listofobjects.Categories.Samplecategory.Content.title(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.listofobjects.categories.samplecategory.content.$index.title", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.Listofobjects.Categories.Samplecategory.detailedDescription(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.listofobjects.categories.samplecategory.detailedDescription", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.Unit.gas(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.unit.gas", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.Unit.Power.u3(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.unit.power.u3", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.Unit.Power.u6(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.unit.power.u6", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
actual fun L.Unit.Power.u9(): String {
    val localizedString = localizationBundle.localizedStringForKey("l.unit.power.u9", null, null)
    return NSString.localizedStringWithFormat(localizedString)
}
