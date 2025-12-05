package com.yavaconf.mcp

import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.service.annotation.PostExchange

interface LightbulbClient {

    @PostExchange(url = "/zeroconf/switch")
    fun switch(@RequestBody body: String)
}
