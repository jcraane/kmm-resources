import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("dev.jamiecraane.plugins.kmmresources")
}

repositories {
    google()
    jcenter()
    maven {
        setUrl("https://jitpack.io")
    }
    maven {
        setUrl("https://maven.google.com/")
    }
}

kotlin {
    android()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.google.android.material:material:1.2.1")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdkVersion(30)
    buildToolsVersion = "30.0.2"

    defaultConfig {
        applicationId = "nl.jcraane.androidapp"
        minSdkVersion(22)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.21")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}

kmmResourcesConfig {
    androidApplicationId.set("nl.jcraane.androidapp")
    androidSourceFolder.set("main")
    packageName.set("com.example.project")
    defaultLanguage.set("nl")
    input.set(File(project.projectDir.path, "generic.yaml"))
    output.set(project.projectDir)
    useDefaultTranslationIfNotInitialized.set(true)
    iosSourceFolder.set("iosMain")
}

val plutil = tasks["executePlutil"]
val generateLocalizations = tasks["generateLocalizations"]
plutil.dependsOn(generateLocalizations)

tasks["preBuild"].dependsOn(plutil)

tasks {
    /**
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
            dependsOn(plutil)
        }
    }

    listOf(
        "linkDebugFrameworkIosSimulatorArm64",
        "linkReleaseFrameworkIosSimulatorArm64",
        "linkDebugFrameworkIosArm64",
        "linkReleaseFrameworkIosArm64",
        "linkDebugFrameworkIosX64",
        "linkReleaseFrameworkIosX64"
    ).forEach { taskName ->
        named(taskName) {
            doFirst {
                val configuration = System.getenv("CONFIGURATION")
                val sdkName = System.getenv("SDK_NAME")

                copy {
                    from("${project.rootDir}/android-app/src/commonMain/resources/ios")
                    into("${project.buildDir}/xcode-frameworks/$configuration/$sdkName/shared.framework")
                }
            }
        }
    }
}
