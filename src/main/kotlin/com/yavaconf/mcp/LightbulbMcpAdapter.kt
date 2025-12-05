package com.yavaconf.mcp

import org.springframework.ai.tool.annotation.Tool
import org.springframework.stereotype.Component

@Component
class LightbulbMcpAdapter(private val lightbulbService: LightbulbService) {

    @Tool(description = "Pulse a lightbulb for 500 ms")
    fun pulse() {
        lightbulbService.pulse()
    }

    @Tool(description = "Pulse a lightbulb n times for 500 ms each")
    fun pulseNTimes(times: Int) {
        lightbulbService.pulse(times)
    }
}
