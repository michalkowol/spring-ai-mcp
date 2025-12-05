package com.yavaconf.mcp

import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import kotlin.reflect.KClass

@Component
class HttpClientFactory(private val restClientBuilder: RestClient.Builder) {

    final inline fun <reified T : Any> create(baseUrl: String): T {
        return create(baseUrl, T::class)
    }

    fun <T : Any> create(baseUrl: String, serviceType: KClass<T>): T {
        val restClient = restClientBuilder
            .baseUrl(baseUrl)
            .build()
        val httpServiceProxyFactory = HttpServiceProxyFactory.builder()
            .exchangeAdapter(RestClientAdapter.create(restClient))
            .build()
        return httpServiceProxyFactory.createClient(serviceType.java)
    }
}

