package com.yavaconf.mcp

import org.springframework.stereotype.Component

@Component
class LightbulbService(private val lightbulbClient: LightbulbClient) {

    fun pulse() {
        val body = """
            {
              "deviceId": "10008fe75b",
              "data": {
                "switch": "on"
              }
            }
        """.trimIndent()
        lightbulbClient.switch(body)
    }

    fun pulse(times: Int) {
        repeat(times) {
            pulse()
            Thread.sleep(1000) // do not do it in prod
        }
    }
}
