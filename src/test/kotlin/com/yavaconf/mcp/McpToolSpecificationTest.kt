package com.yavaconf.mcp

import assertk.assertThat
import assertk.assertions.hasSize
import io.modelcontextprotocol.server.McpServerFeatures.SyncToolSpecification
import io.modelcontextprotocol.spec.McpSchema.CallToolRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier

class McpToolSpecificationTest : IntegrationTest() {

    @Autowired
    @Qualifier("toolSpecs")
    protected lateinit var toolSpecifications: List<SyncToolSpecification>

    @Test
    fun `should register MCP tools`() {
        // then
        assertThat(toolSpecifications).hasSize(2)
        assertThat(toolSpecifications).containsToolNamed("pulse")
        assertThat(toolSpecifications).containsToolNamed("pulseNTimes")
    }

    @Test
    fun `pulse tool should have correct description`() {
        // then
        assertThat(toolSpecifications).toolNamed("pulse")
            .hasDescription("Pulse a lightbulb for 500 ms")
    }

    @Test
    fun `pulseNTimes tool should have correct description`() {
        // then
        assertThat(toolSpecifications).toolNamed("pulseNTimes")
            .hasDescription("Pulse a lightbulb n times for 500 ms each")
    }

    @Test
    fun `should be able to call pulse tool`() {
        // given
        val pulseTool = toolSpecifications.first { it.tool().name() == "pulse" }

        // when
        pulseTool.callHandler().apply(null, CallToolRequest.builder("pulse").build())

        // then
        assertThat(lightbulbClient).hasRecordedCalls(1)
    }

    @Test
    fun `should be able to call pulseNTimes tool`() {
        // given
        val pulseNTimesTool = toolSpecifications.first { it.tool().name() == "pulseNTimes" }

        // when
        pulseNTimesTool.callHandler().apply(null, CallToolRequest.builder("pulseNTimes").arguments(mapOf("times" to 2)).build())

        // then
        assertThat(lightbulbClient).hasRecordedCalls(2)
    }
}
