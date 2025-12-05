package com.yavaconf.mcp

import assertk.assertThat
import assertk.assertions.hasSize
import org.junit.jupiter.api.Test
import org.springframework.ai.tool.ToolCallbackProvider
import org.springframework.beans.factory.annotation.Autowired

class McpToolCallbackTests : IntegrationTest() {

    @Autowired
    private lateinit var toolCallbackProvider: ToolCallbackProvider

    @Test
    fun `should register MCP tools`() {
        // when
        val toolCallbacks = toolCallbackProvider.toolCallbacks

        // then
        assertThat(toolCallbacks).hasSize(2)
        assertThat(toolCallbacks).containsToolNamed("pulse")
        assertThat(toolCallbacks).containsToolNamed("pulseNTimes")
    }

    @Test
    fun `pulse tool should have correct description`() {
        // when
        val toolCallbacks = toolCallbackProvider.toolCallbacks

        // then
        assertThat(toolCallbacks).toolNamed("pulse")
            .hasDescription("Pulse a lightbulb for 500 ms")
    }

    @Test
    fun `pulseNTimes tool should have correct description`() {
        // when
        val toolCallbacks = toolCallbackProvider.toolCallbacks

        // then
        assertThat(toolCallbacks).toolNamed("pulseNTimes")
            .hasDescription("Pulse a lightbulb n times for 500 ms each")
    }

    @Test
    fun `should be able to call pulse tool via ToolCallback`() {
        // given
        val pulseTool = toolCallbackProvider.toolCallbacks.first { it.toolDefinition.name() == "pulse" }

        // when
        pulseTool.call("{}")

        // then
        assertThat(lightbulbClient).hasRecordedCalls(1)
    }

    @Test
    fun `should be able to call pulseNTimes tool via ToolCallback`() {
        // given
        val pulseNTimesTool = toolCallbackProvider.toolCallbacks.first { it.toolDefinition.name() == "pulseNTimes" }

        // when
        pulseNTimesTool.call("""{"times": 2}""")

        // then
        assertThat(lightbulbClient).hasRecordedCalls(2)
    }
}
