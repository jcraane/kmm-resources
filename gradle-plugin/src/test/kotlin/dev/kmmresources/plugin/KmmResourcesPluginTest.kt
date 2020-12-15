package dev.kmmresources.plugin

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertNotNull

/**
 * A simple unit test for the 'tmp.greeting' plugin.
 */
class KmmResourcesPluginTest {
    @Test fun `plugin registers task`() {
        // Create a test project and apply the plugin
        val project = ProjectBuilder.builder().build()
        project.plugins.apply("dev.kmmresources.plugin")

        // Verify the result
        assertNotNull(project.tasks.findByName("generate-kmm-resources"))
    }
}
