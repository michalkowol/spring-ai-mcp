package com.yavaconf.mcp

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
abstract class IntegrationTest {

    @Autowired
    protected lateinit var lightbulbClient: FakeLightbulbClient

    @BeforeEach
    fun resetFakeLightbulbClient() {
        lightbulbClient.reset()
    }
}
