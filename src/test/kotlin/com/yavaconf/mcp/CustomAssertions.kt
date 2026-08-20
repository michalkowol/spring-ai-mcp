package com.yavaconf.mcp

import assertk.Assert
import assertk.assertions.support.expected
import assertk.assertions.support.show
import io.modelcontextprotocol.server.McpServerFeatures.SyncToolSpecification

fun Assert<SyncToolSpecification>.hasDescription(expected: String): Unit = given { actual ->
    val actualDescription = actual.tool().description()
    if (actualDescription == expected) return
    expected("description:${show(expected)} but was description:${show(actualDescription)}")
}

fun Assert<List<SyncToolSpecification>>.containsToolNamed(name: String): Unit = given { actual ->
    val toolNames = actual.map { it.tool().name() }
    when (toolNames.count { it == name }) {
        1 -> return
        0 -> expected("to contain tool named:${show(name)} but was:${show(toolNames)}")
        else -> expected("to contain exactly one tool named:${show(name)} but was:${show(toolNames)}")
    }
}

fun Assert<List<SyncToolSpecification>>.toolNamed(name: String): Assert<SyncToolSpecification> = transform { actual ->
    val toolNames = actual.map { it.tool().name() }
    when (toolNames.count { it == name }) {
        1 -> actual.first { it.tool().name() == name }
        0 -> expected("to contain tool named:${show(name)} but tools were:${show(toolNames)}")
        else -> expected("to contain exactly one tool named:${show(name)} but tools were:${show(toolNames)}")
    }
}

fun Assert<FakeLightbulbClient>.hasRecordedCalls(count: Int): Unit = given { actual ->
    if (actual.switchCalls.size == count) return
    expected("to have ${show(count)} recorded calls but had ${show(actual.switchCalls.size)}")
}
