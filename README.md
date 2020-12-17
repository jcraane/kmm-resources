# KMM Resources

Generator including Gradle plugin to generate KMM (Kotlin Multiplatform Mobile) resources for iOS and Android. This tool generates Android xml string resources and iOS localizable strings based on a single YAML file that includes all the localized strings to be used in your KMM app. It also generates Kotlin code for your common module to refer statically to those strings (similar to R. in Android).


## Usage

TODO



## Samples

### Simple strings

```yaml
general:
  button:
    ok:
      en: "OK"
      nl: "OK"
    yes:
      en: "Yes"
      nl: "Ja"
    no:
      en: "No"
      nl: "Nee"
myView:
  title:
    en: My title
    nl: Mijn titel
  detail:
    button:
      previous:
        en: Previous
        nl: Vorige
      next:
        en: Next
        nl: Volgende
```

Generates:

```kotlin
class L {
  companion object {
    val general: General = General()
    val myView: MyView = MyView()
  }
  data class General(
    val button: Button = Button()) {
    class Button
  }
  data class MyView(
    val detail: Detail = Detail()) {
    data class Detail(
      val button: Button = Button()) {
      class Button
    }
  }
}


expect fun L.General.Button.no(): String
expect fun L.General.Button.ok(): String
expect fun L.General.Button.yes(): String
expect fun L.MyView.Detail.Button.next(): String
expect fun L.MyView.Detail.Button.previous(): String
expect fun L.MyView.title(): String
```