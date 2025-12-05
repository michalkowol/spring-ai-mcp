package com.yavaconf.mcp

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app.lightbulb")
data class LightbulbProperties(
    val baseUrl: String = "http://localhost:8081"
)
