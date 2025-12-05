package com.yavaconf.mcp

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LightbulbController(private val lightbulbService: LightbulbService) {

    @GetMapping("/")
    fun pulse() {
        lightbulbService.pulse()
    }
}
