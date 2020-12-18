# KMM Resources

Generator including Gradle plugin to generate KMM (Kotlin Multiplatform Mobile) resources for iOS and Android. This tool generates Android xml string resources and iOS localizable strings based on a single YAML file that includes all the localized strings to be used in your KMM app. It also generates Kotlin code for your common module to refer statically to those strings (similar to R. in Android).


## Usage

TODO

### Android

The generated localization files contains two parts:

1. The generated strings.xml file for every locale
2. An object L which is used to reference all resources statically during compile time (just as Android R class).

The resources are prefixed bij l (lowercase L) and can be access like that in xml:

```xml
android:text="@string/l.greetings.hello"
```

To access the resources from code use the generated L class like in the following example:

```kotlin
L.greetings.hello()
```

The hello function is an expected function in commonMain which has an actual implementation for every target. In Android the actual function looksup the resources in the generated strings xml files.

For this to work the localizationContext (an Android Context) needs to be initialized. This is typically done in the application like in the following example:

```kotlin
import android.app.Application
import com.example.project.localizationContext

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // localizationContext is defined in the KMMResourcesLocalization.kt file which contains the actual implementations of the resource functions. 
        localizationContext = this
    }
}
```

### iOS

The generated localization files are compiled into a binary format using `plutil` and then copied into the shared FAT framework. To access those string during runtime from the shared module we need access to the Bunlde of the framework.

Unfortunately at the time of writing we cannot access the framework Bundle correctly from Kotlin code. Therefore the following code is generated that sets the bundle to the (incorrect) main bundle.
```kotlin
var localizationBundle = NSBundle.mainBundle()
```

To reference the correct Bundle, you need to add the following code at launch time of your iOS app.
```swift
import shared

...

// put this in `application(:didFinishLaunchingWithOptions:)` or in the init of your SwiftUI app
KMMResourcesKt.localizationBundle = Bundle(for: L.self)
```

#### Accessing strings

The generated `L` class contains a companion object to be able to statically access the strings (see Samples). Therefore to right way to access a string from Kotlin code is as follows:

```kotlin
val string = L.general.button.ok()
```

However this does not work in iOS since you cannot access companion variables directly. Instead you access the through the `Companion()`, like this:
```swift
let string = L.Companion().general.button.ok()
```

To make things a bit more convenient we suggest to add the following extension to your app:
```swift
import shared

public extension L {
    static var c: L.Companion {
        return L.Companion()
    }
}
```

Now you can access strings as follows:

```swift
let string = L.c.general.button.ok()
```

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

Usages:

```kotlin
val string = L.general.button.ok()
```

### Strings with arguments

```yaml
myView:
  detail:
    priceOfItem:
      en: Item price of %1$s is %2$s
      nl: Prijs is %2$s voor %1$s
```

Generates:

```kotlin
class L {
  companion object {
    val myView: MyView = MyView()
  }
  data class MyView(
    val detail: Detail = Detail()) {
    class Detail
  }
}


expect fun L.MyView.Detail.priceOfItem(value0: String, value1: String): String
```

Usages:

```kotlin
val string = L.myView.detail.priceOfItem("laptop", "€ 2,00") // Item price of laptop is € 2,00
```

### List of strings

```yaml
myView:
  myList:
    -
      en: Usage
      nl: Gebruik
    -
      en: Features
      nl: Kenmerken
    -
      en: Samples
      nl: Voorbeelden
```

Generates:

```kotlin
class L {
  companion object {
    val myView: MyView = MyView()
  }
  class MyView
}


expect fun L.MyView.myList(): List<String>
```

Usage:

```kotlin
val strings = L.myView.myList()
strings.size() // 3
print(strings[0]) // Usage
```

### List of objects

```yaml
myView:
  myList:
    - 0:
        title:
          en: Usage
          nl: Gebruik
        subtitle:
          en: Subtitle of usage
          nl: Ondertitel van gebruik
    - 1:
        title:
          en: Features
          nl: Kenmerken
        subtitle:
          en: Subtitle of features
          nl: Ondertitel van kenmerken
    - 2:
        title:
          en: Samples
          nl: Voorbeelden
        subtitle:
          en: Subtitle of samples
          nl: Ondertitel van voorbeelden
```

Generates:

```kotlin
// Generated by KMM Resources
package com.example.common.localization

class L {
  companion object {
    val myView: MyView = MyView()
  }
  data class MyView(
    val myList: List<MyList> = listOf(MyList(0), MyList(1), MyList(2))
) {
    data class MyList(val index: Int)
  }
}


expect fun L.MyView.MyList.subtitle(): String
expect fun L.MyView.MyList.title(): String
```

Usage:

```kotlin
val objects = L.myView.myList
print(objects[1].title()) // Features
print(objects[1].subtitle()) // Subtitle of features
```
