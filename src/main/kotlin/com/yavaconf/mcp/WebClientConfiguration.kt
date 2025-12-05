package com.yavaconf.mcp

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
class WebClientConfiguration(private val webClientFactory: WebClientFactory) {

    @Bean
    fun lightbulbClient(): LightbulbClient {
        return webClientFactory.create(baseUrl = "http://172.18.0.67:8081")
    }
}
