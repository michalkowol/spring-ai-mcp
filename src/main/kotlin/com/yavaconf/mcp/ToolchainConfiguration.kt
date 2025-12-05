package com.yavaconf.mcp

import org.springframework.context.annotation.Configuration
import org.springframework.ai.tool.ToolCallbackProvider
import org.springframework.ai.tool.method.MethodToolCallbackProvider
import org.springframework.context.annotation.Bean


@Configuration(proxyBeanMethods = false)
class ToolchainConfiguration {

    @Bean
    fun toolCallbackProvider(lightbulbMcpAdapter: LightbulbMcpAdapter): ToolCallbackProvider {
        return MethodToolCallbackProvider.builder()
            .toolObjects(lightbulbMcpAdapter)
            .build()
    }
}
