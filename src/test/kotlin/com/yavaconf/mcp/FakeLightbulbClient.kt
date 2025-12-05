package com.yavaconf.mcp

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Component
@Primary
class FakeLightbulbClient : LightbulbClient {

    val switchCalls = mutableListOf<String>()

    override fun switch(body: String) {
        switchCalls.add(body)
    }

    fun reset() {
        switchCalls.clear()
    }
}
