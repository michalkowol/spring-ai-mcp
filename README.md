# Spring AI MCP Lightbulb Controller

A demonstration project showcasing how to build an **MCP (Model Context Protocol) server** using **Spring AI** and **Kotlin** to control a smart lightbulb via AI assistants.

## Overview

This project creates an MCP server that exposes tools for controlling a smart lightbulb. It can be used by AI assistants (such as Cursor, Claude Desktop, or other MCP-compatible clients) to interact with IoT devices through natural language commands.

## Features

- **MCP Server** - Exposes lightbulb control as AI-callable tools
- **Smart Lightbulb Integration** - Controls lightbulbs via HTTP API (compatible with Sonoff DIY mode and similar devices)
- **Spring Boot 3.5** - Built on modern Spring Boot with Kotlin
- **Spring AI 1.1** - Leverages Spring AI's MCP server support

## Available MCP Tools

| Tool | Description |
|------|-------------|
| `pulse` | Pulse a lightbulb for 500 ms |
| `pulseNTimes` | Pulse a lightbulb n times for 500 ms each |

## Configuration

### Lightbulb Connection

Update the lightbulb IP address in `WebClientConfiguration.kt`:

```kotlin
fun lightbulbClient(): LightbulbClient {
    return webClientFactory.create(baseUrl = "http://YOUR_LIGHTBULB_IP:8081")
}
```

### MCP Server Settings

The MCP server is configured in `application.properties`:

```properties
spring.ai.mcp.server.protocol=streamable
spring.ai.mcp.server.streamable-http.mcp-endpoint=/mcp
```

## Running the Application

```bash
./gradlew bootRun
```

The MCP server will be available at `http://localhost:8080/mcp`

## Using with AI Assistants

### Cursor IDE

Add the following to your MCP settings:

```json
{
  "mcpServers": {
    "lightbulb": {
      "url": "http://localhost:8080/mcp"
    }
  }
}
```

Once configured, you can ask the AI assistant to "pulse the lightbulb" or "pulse the lightbulb 3 times".

## License

See [LICENSE](LICENSE) file.

