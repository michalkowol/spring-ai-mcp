package com.yavaconf.mcp

import org.springframework.ai.mcp.annotation.McpTool
import org.springframework.ai.mcp.annotation.McpTool.McpAnnotations
import org.springframework.ai.mcp.annotation.McpToolParam
import org.springframework.stereotype.Component

@Component
class LightbulbMcpAdapter(private val lightbulbService: LightbulbService) {

    @McpTool(
        description = "Pulse a lightbulb for 500 ms",
        generateOutputSchema = true,
        annotations = McpAnnotations(
            readOnlyHint = false,
            destructiveHint = true,
            idempotentHint = false,
            openWorldHint = false
        )
    )
    fun pulse() {
        lightbulbService.pulse()
    }

    @McpTool(
        description = "Pulse a lightbulb n times for 500 ms each",
        generateOutputSchema = true,
        annotations = McpAnnotations(
            readOnlyHint = false,
            destructiveHint = true,
            idempotentHint = false,
            openWorldHint = false
        )
    )
    fun pulseNTimes(@McpToolParam(description = "Times", required = true) times: Int) {
        lightbulbService.pulse(times)
    }
}
