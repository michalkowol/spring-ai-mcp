package com.yavaconf.mcp

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LightbulbController(private val lightbulbService: LightbulbService) {

    @PostMapping("/pulse")
    fun pulse() {
        lightbulbService.pulse()
    }
}
