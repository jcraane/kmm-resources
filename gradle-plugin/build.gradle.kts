plugins {
    kotlin("jvm") version "1.4.20"
    `java-gradle-plugin`
}

gradlePlugin {
    plugins {
        create("simplePlugin") {
            id = "org.samples.greeting"
            implementationClass = "org.gradle.GreetingPlugin"
        }
    }
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib", "1.4.20"))
}
