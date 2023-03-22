# KMM Resources

Generator including Gradle plugin to generate KMM (Kotlin Multiplatform Mobile) resources for iOS, Android and Web (Javascript). This tool generates Android xml string resources, iOS localizable strings and Javascript localization files based on a single YAML file that includes all the localized strings to be used in your KMM app. It also generates Kotlin code for your common module to refer statically to those strings (similar to R. in Android).

Although this plugin generates resources for both Android, iOS and Web, it is not mandatory to have both an Android, iOS or Web application present. For example, the Android app can be developed first using Kotlin Multiplatform Mobile and kmm-resources to which an iOS app is added later.

## Upgrading/release

### Upgrade to alpha08

- As of version alpha08 the ' (single quote) does not need to be escaped in the yaml anymore. When updating to version alpha08 replace ```\'``` in your yaml file with ```'``` (single quote without escaping)**

### 1.0.0-alpha10

- -Files can now be written to every defined path instead of paths only ending with *Main

## Prerequisites

- If using this plugin for iOS, OSX is required since it depends on the plutil command line utility to convert Localizable.strings to a binary representation.

## Configuration

### Using the plugin via Gradle Plugin Portal

The KMMResources  plugin is published via the Gradle plugin portal and can be found here: https://plugins.gradle.org/plugin/dev.jamiecraane.plugins.kmmresources

Add the following plugin definition to start using the plugin:

```kotlin
plugins {
  id "dev.jamiecraane.plugins.kmmresources" version "1.0.0-alpha10"
}
```

See the [Plugin page](https://plugins.gradle.org/plugin/dev.jamiecraane.plugins.kmmresources) for more instructions if needed.

### Deploy and include plugin locally

When developing this plugin (or when forking this plugin and adding code to it) you may want to test this plugin in a project not included in this composite build. To do this do the following:

Navigate to the plugin-build folder and execute ```./gradlew :kmmresources:publishToMavenLocal```. This task publishes to the local maven repository.The local Maven repository stores build artefacts used by Maven and Gradle and can be found at ~/.m2/repository

Navigate to the ~/.m2/repository/dev/jamiecraane/plugins/kmmresources to see the published plugins.

In the project in which the plugin must be integrated do the following:

1. In settings.gradle.kts make sure the mavenLocal() repo is present in the pluginManagement section:

```kotlin
pluginManagement {
    repositories {
        mavenLocal()
    }
}
```

Apply the plugin:

```kotlin
plugins {
    id("dev.jamiecraane.plugins.kmmresources") version "1.0.0-alpha10"
}
```

### Configuration of the plugin itself

#### Configure the plugin for Android:

```kotlin
kmmResourcesConfig {
    androidApplicationId.set("com.example.app")
    packageName.set("com.example.app.shared.localization")
    defaultLanguage.set("nl")
    input.set(File(project.projectDir.path, "generic.yaml"))
    output.set(project.projectDir)
    srcFolder.set("src") // Optional, defaults to 'src'
    generatedClassName.set("KMMResourcesLocalization.kt") // Optional, defaults to 'KMMResourcesLocalization.kt'
    androidStringsPrefix.set("_generated") // Optional, defaults to '_generated'
    androidSourceFolder.set("main") // The location of the android sources in the shared module (Optional, defaults to androidMain)
    useDefaultTranslationIfNotInitialized.set(true) // When true, outputs the texts of the default language in the Android generated actual declarations instead of an empty String
}

val plutil = tasks["executePlutil"] // This one is only needed for iOS

val generateLocalizations = tasks["generateLocalizations"]
tasks["preBuild"].dependsOn(generateLocalizations)
```
This configures the plugin so it runs before the preBuild stage. This is soon enough to generate the localizations so they can be found during build time.

**Initialize the localizationContext:**

```kotlin
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // localizationContext is defined in the KMMResourcesLocalization.kt file which contains the actual implementations of the resource functions.
        localizationContext = this
    }
}
```

Make sure the custom MyApplication is registered in the manifest.

#### Configure for iOS

Configure the plugin for iOS (add the compileKotlinIos* based on the targets that the app supports):

```kotlin
tasks {
/*
     * This sets up dependencies between the plutil task and compileKotlinIos* tasks. This
     * way common is recompiled if something in generic.yaml changes (so new ios resources
     * are generated). If the generic.yaml file is not changed, the resources are considered
     * up to date by Gradle.

 */
    listOf(
        "compileKotlinIosArm64",
        "compileKotlinIosX64",
        "compileKotlinIosSimulatorArm64"
    ).forEach { taskName ->
        named(taskName) {
            dependsOn(plutil, generateImages)
        }
    }
}
```

To copy the generated resources into the framework add the following to your build file as well:

```kotlin
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink>().configureEach {
    if (outputKind != org.jetbrains.kotlin.konan.target.CompilerOutputKind.FRAMEWORK) return@configureEach
    val iosResources = project.layout.buildDirectory.dir("src/commonMain/resources/ios")

    doLast {
        iosResources.get().asFile.copyRecursively(outputFile.get(), true)
    }
}
```

**Initialize the localizationBundle**

In the AppDelegate initialize the bundle like in the following example:

```
import UIKit
import shared

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        KMMResourcesLocalizationKt.localizationBundle = Bundle(for: L.self) // Bundle is initialized here
        return true
    }

    // MARK: UISceneSession Lifecycle

    func application(_ application: UIApplication, configurationForConnecting connectingSceneSession: UISceneSession, options: UIScene.ConnectionOptions) -> UISceneConfiguration {
        return UISceneConfiguration(name: "Default Configuration", sessionRole: connectingSceneSession.role)
    }

    func application(_ application: UIApplication, didDiscardSceneSessions sceneSessions: Set<UISceneSession>) {
         discarded scenes, as they will not return.
    }
}
```

## Usage

All projects always use the same YAML file, as configured in the plugin, that contains all localized strings. Below the usage for each platform. Check the [Samples](#samples) section for YAML samples of the localization file.

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
// or
L.greetings["hello"]
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

*Return default language resource in Android instead of empty String if Android context is not initialized*

By setting the option useDefaultTranslationIfNotInitialized in the kmmResourceConfig (in Gradle build) to true, the Android actual declarations will output
the texsts of the default language instead of an empty String if the Android context is not initialized. This can be useful in plain unit tests
where no Android context is available and you want to test that a specific String is returned from the common localization methods.

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

### Javascript

Since Javascript is lacking a standard localization method the tool is simply generating a single map that contains all localized strings for all languages.

Just like on iOS and Android, to access the resources from code use the generated L class like in the following example:

```kotlin
L.greetings.hello()
```

The actual implementation of this will fetch the correct string based on the current language (see next section).

#### Language resolution

The current language will be resolved using the following steps:

1. Manually set the `currentLanguage` variable that's generated in the `KMMResourcesLocalization.kt`. This can be done for example from pressing a language button on your website which can then be remembered in a cookie or local storage. By default `currentLanguage` is `null` and once set will overwrite the option below.

2. If `currentLanguage` is `null`, `window.navigator.languages` will be evaluated and the first language is this array will be used as language. This should be supported by all modern browsers.

In case a full ISO language and country classifier is used, such as `en-US`, both `en-US` and `en` will be used to find matching strings.

In case the above methods do not find a matching string, the `defaultLanguage` configured for the plugin will be used as fallback language. For example if the `defaultLanguage` is `en` and the browser `window.navigator.languages` returns an unsupported language such as `nl_NL`, then `en` will be used as fallback language.


## Samples

Samples of the YAML localization file.

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



## Common issues

# Android sources not generated
Please check the folder in which the Android sources reside. For a new KMM project htis defaults to androidMain. The plugin also defaults to androidMain. If the Android sources reside in a different folder please cconfigure this using the following property:

```kotlin
kmmResourcesConfig {
    androidSourceFolder.set("main") // The location of the android sources in the shared module (Optional, defaults to androidMain)
}
```
