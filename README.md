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

Configure the lightbulb base URL in `application.properties`:

```properties
app.lightbulb.base-url=http://ewelink_10008fe75b.local:8081
```

> **Note:** Some MCP clients may have trouble resolving mDNS hostnames. If you experience connection issues, use the IP address instead of the `.local` hostname.

To get the IP address from the mDNS hostname:

```sh
ping -c 1 -a ewelink_10008fe75b.local
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

## Sonoff DIY Mode Setup

### Initial Setup

1. Restart Sonoff device - it should blink every 1s
2. Connect to `ITEAD-xxxxx` WiFi network using password `12345678`
3. Open http://10.10.7.1/ to configure the device

### API Examples

#### Switch

```http
POST http://ewelink_10008fe75b.local:8081/zeroconf/switch
Content-Type: application/json

{
  "deviceId": "10008fe75b",
  "data": {
    "switch": "on"
  }
}
```

#### Pulse (multiples of 500ms)

```http
POST http://ewelink_10008fe75b.local:8081/zeroconf/pulse
Content-Type: application/json

{ 
    "deviceid": "10008fe75b", 
    "data": {
        "pulse": "on", 
        "pulseWidth": 500
    } 
}
```

## License

See [LICENSE](LICENSE) file.
