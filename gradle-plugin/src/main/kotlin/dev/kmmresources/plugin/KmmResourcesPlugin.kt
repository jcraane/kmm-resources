package dev.kmmresources.plugin

import org.gradle.api.Project
import org.gradle.api.Plugin
import dev.kmmresources.core.Library

/**
 * A simple 'hello world' plugin.
 */
class KmmResourcesPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        // Register a task
        project.tasks.register("generate-kmm-resources") { task ->
            task.doLast {
                println("Hello from plugin 'tmp.greeting'")
                val l = Library()
                l.someLibraryMethod()
            }
        }
    }
}
