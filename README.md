# Solenopsis SOAP

Java library containing Salesforce SOAP web service clients for all major Salesforce APIs.

[![Build Status](https://github.com/solenopsis/soap/workflows/CD-CI/badge.svg)](https://github.com/solenopsis/soap/actions)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

## Overview

This library provides pre-generated SOAP clients for Salesforce APIs using Apache CXF. It automatically generates Java classes from Salesforce WSDL files for:

- **Apex API** - Execute anonymous Apex code and manage logs
- **Enterprise API** - Full-featured data API with strong typing
- **Metadata API** - Deploy and retrieve metadata (custom objects, Apex classes, etc.)
- **Partner API** - Flexible data API with dynamic typing
- **Tooling API** - Developer tools and schema introspection

## Features

- Pre-generated SOAP clients from official Salesforce WSDLs
- Factory pattern for easy service/port creation
- Enum-based API for type-safe service selection
- QName management for web service endpoints
- Built on [FlossWare Commons Java](https://github.com/FlossWare/commons-java) for SOAP utilities

## Installation

### Maven

```xml
<dependency>
    <groupId>org.solenopsis</groupId>
    <artifactId>soap</artifactId>
    <version>1.22</version>
</dependency>

<repositories>
    <repository>
        <id>flossware-packagecloud</id>
        <url>https://packagecloud.io/flossware/java/maven2</url>
    </repository>
</repositories>
```

## Usage Examples

### Create a Metadata API Port

```java
import org.solenopsis.soap.port.factory.PortFactoryEnum;
import org.solenopsis.soap.metadata.MetadataPortType;

// Create port with default factory
MetadataPortType port = PortFactoryEnum.METADATA.createPort();

// Or with specific endpoint URL
MetadataPortType port = PortFactoryEnum.METADATA.createPort(
    "https://na1.salesforce.com/services/Soap/m/58.0"
);
```

### Use Service Enums

```java
import org.solenopsis.soap.service.ServiceEnum;

// Get service information
ServiceEnum metadata = ServiceEnum.METADATA;
QName qname = metadata.getQName().getQName();
ServiceWsdlEnum wsdl = metadata.getWsdl();
ServiceFactoryEnum factory = metadata.getFactory();
```

### Access All API Types

```java
// Apex API
ApexPortType apexPort = PortFactoryEnum.APEX.createPort(url);

// Enterprise API
Soap enterprisePort = PortFactoryEnum.ENTERPRISE.createPort(url);

// Metadata API
MetadataPortType metadataPort = PortFactoryEnum.METADATA.createPort(url);

// Partner API  
Soap partnerPort = PortFactoryEnum.PARTNER.createPort(url);

// Tooling API
SforceServicePortType toolingPort = PortFactoryEnum.TOOLING.createPort(url);
```

### Error Handling

```java
// URL validation — throws IllegalArgumentException for invalid URLs
try {
    MetadataPortType port = PortFactoryEnum.METADATA.createPort(endpointUrl);
} catch (IllegalArgumentException e) {
    // Thrown for null, empty, blank, malformed, or non-absolute URLs
    log.error("Invalid endpoint URL: {}", e.getMessage());
}

// SOAP call errors — standard JAX-WS exceptions
try {
    port.listMetadata(queries, apiVersion);
} catch (WebServiceException e) {
    if (e.getCause() instanceof java.net.ConnectException) {
        // Network connectivity failure
    } else if (e.getCause() instanceof java.net.SocketTimeoutException) {
        // Request timeout
    }
    // Other transport-level errors (SSL, DNS, etc.)
} catch (SOAPFaultException e) {
    // Salesforce returned a SOAP fault (invalid session, permission denied, etc.)
    SOAPFault fault = e.getFault();
    log.error("SOAP fault: {} - {}", fault.getFaultCode(), fault.getFaultString());
}
```

**Common exceptions:**

| Exception | When | Recovery |
|-----------|------|----------|
| `IllegalArgumentException` | Invalid URL passed to `createPort(String)` | Fix the URL |
| `WebServiceException` | Network/transport errors during SOAP calls | Retry with backoff |
| `SOAPFaultException` | Salesforce returned an error response | Check fault code |
| `IllegalStateException` | WSDL resource not found on classpath | Check JAR packaging |

## Architecture

### Package Structure

```
org.solenopsis.soap
├── apex/              # Generated Apex API classes
├── enterprise/        # Generated Enterprise API classes
├── metadata/          # Generated Metadata API classes
├── partner/           # Generated Partner API classes
├── tooling/           # Generated Tooling API classes
├── port/
│   └── factory/       # Port factory implementations
└── service/
    └── factory/       # Service factory implementations
```

### Code Generation

SOAP clients are automatically generated during the Maven build using the `cxf-codegen-plugin`:

```bash
mvn clean install
```

This reads WSDL files from `src/main/resources/wsdl/` and generates Java classes in `target/generated-sources/`.

## WSDL Files

The library includes Salesforce WSDL files for:

- `soap-apex.wsdl` (25KB) - Apex API
- `soap-enterprise.wsdl` (3.0MB) - Enterprise API  
- `soap-metadata.wsdl` (1.2MB) - Metadata API
- `soap-partner.wsdl` (557KB) - Partner API
- `soap-tooling.wsdl` (1.9MB) - Tooling API

These are official Salesforce WSDL files that define the SOAP interfaces.

## Requirements

- **Java 17+**
- **Apache CXF 4.0.11** - SOAP framework
- **FlossWare Commons Java 1.37** - Foundation utilities
- **Apache Commons Lang3 3.20.0**

## Testing

The library includes comprehensive unit tests:

```bash
# Run all tests
mvn test

# Build without tests
mvn clean install -DskipTests
```

**Test Coverage:**
- Port factory enum tests - 12 tests
- Port factory implementation tests - 10 tests (Apex, Enterprise, Metadata, Partner, Tooling)
- Port class tests - 7 tests
- Port method tests - 8 tests
- Service enum tests - 7 tests  
- Service QName tests - 7 tests
- Service WSDL tests - 10 tests
- **Total: 61 tests** with 0 failures

**Custom Code Coverage:**
- org.solenopsis.soap.service: 100%
- org.solenopsis.soap.service.factory: 100%
- org.solenopsis.soap.port: 100%
- org.solenopsis.soap.port.factory: 100%

## Dependencies

This library is used by:
- **[solenopsis/session](https://github.com/solenopsis/session)** - Salesforce session management

And depends on:
- **[FlossWare/commons-java](https://github.com/FlossWare/commons-java)** - SOAP and utility functions

## Building from Source

```bash
git clone https://github.com/solenopsis/soap.git
cd soap
mvn clean install
```

The build process:
1. Reads WSDL files from `src/main/resources/wsdl/`
2. Generates Java classes using `cxf-codegen-plugin`
3. Compiles generated and custom code
4. Runs tests
5. Packages JAR

## API Version

The library currently supports Salesforce API version as defined in the WSDL files. To update:

1. Download latest WSDLs from Salesforce
2. Replace files in `src/main/resources/wsdl/`
3. Rebuild: `mvn clean install`

## Contributing

1. Ensure all tests pass: `mvn test`
2. Follow existing code patterns
3. Add tests for new functionality
4. Update documentation

## Documentation

- **[API Reference](API.md)** - Complete API documentation with examples
- **[Architecture](ARCHITECTURE.md)** - Design patterns and architecture decisions

## License

GNU General Public License, Version 3 - See [LICENSE](LICENSE) file

## Links

- **Source**: https://github.com/solenopsis/soap
- **Issues**: https://github.com/solenopsis/soap/issues
- **Session Management**: https://github.com/solenopsis/session
- **Commons Library**: https://github.com/FlossWare/commons-java
- **Package Repository**: https://packagecloud.io/flossware/java

## Salesforce API Documentation

- [Apex API Guide](https://developer.salesforce.com/docs/atlas.en-us.apexcode.meta/apexcode/)
- [Enterprise WSDL](https://developer.salesforce.com/docs/atlas.en-us.api.meta/api/)
- [Metadata API Guide](https://developer.salesforce.com/docs/atlas.en-us.api_meta.meta/api_meta/)
- [Partner WSDL](https://developer.salesforce.com/docs/atlas.en-us.api.meta/api/)
- [Tooling API Guide](https://developer.salesforce.com/docs/atlas.en-us.api_tooling.meta/api_tooling/)
