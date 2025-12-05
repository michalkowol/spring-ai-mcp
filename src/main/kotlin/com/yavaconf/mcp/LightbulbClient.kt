package com.yavaconf.mcp

import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.service.annotation.PostExchange

interface LightbulbClient {

    // POST http://172.18.0.67:8081/zeroconf/switch
    @PostExchange(url = "/zeroconf/switch")
    fun switch(@RequestBody body: String)
}
