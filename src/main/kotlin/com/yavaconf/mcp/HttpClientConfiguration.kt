package com.yavaconf.mcp

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
class HttpClientConfiguration(private val httpClientFactory: HttpClientFactory) {

    @Bean
    fun lightbulbClient(): LightbulbClient {
        return httpClientFactory.create(baseUrl = "http://172.18.0.67:8081")
    }
}

