package com.yavaconf.mcp

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
class HttpClientConfiguration(
    private val httpClientFactory: HttpClientFactory,
    private val lightbulbProperties: LightbulbProperties
) {

    @Bean
    fun lightbulbClient(): LightbulbClient {
        return httpClientFactory.create(baseUrl = lightbulbProperties.baseUrl)
    }
}
