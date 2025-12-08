package com.yavaconf.mcp

import assertk.Assert
import assertk.assertions.support.expected
import assertk.assertions.support.show
import org.springframework.ai.tool.ToolCallback

fun Assert<ToolCallback>.hasDescription(expected: String) = given { actual ->
    val actualDescription = actual.toolDefinition.description()
    if (actualDescription == expected) return
    expected("description:${show(expected)} but was description:${show(actualDescription)}")
}

fun Assert<Array<ToolCallback>>.containsToolNamed(name: String) = given { actual ->
    val toolNames = actual.map { it.toolDefinition.name() }
    if (toolNames.contains(name)) return
    expected("to contain tool named:${show(name)} but was:${show(toolNames)}")
}

fun Assert<Array<ToolCallback>>.toolNamed(name: String): Assert<ToolCallback> = transform { actual ->
    actual.firstOrNull { it.toolDefinition.name() == name }
        ?: expected("to contain tool named:${show(name)} but tools were:${show(actual.map { it.toolDefinition.name() })}")
}

fun Assert<FakeLightbulbClient>.hasRecordedCalls(count: Int) = given { actual ->
    if (actual.switchCalls.size == count) return
    expected("to have ${show(count)} recorded calls but had ${show(actual.switchCalls.size)}")
}

