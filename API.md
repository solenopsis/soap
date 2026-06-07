# Solenopsis SOAP API Reference

Complete API reference for the Solenopsis SOAP library.

## Table of Contents

- [Overview](#overview)
- [Port Factory API](#port-factory-api)
- [Service Factory API](#service-factory-api)
- [Service Metadata API](#service-metadata-api)
- [Port Metadata API](#port-metadata-api)
- [Supported Salesforce APIs](#supported-salesforce-apis)

## Overview

The Solenopsis SOAP library provides a clean, type-safe API for working with Salesforce SOAP web services. The library is organized into several layers:

1. **Port Factory Layer** - High-level API for creating SOAP ports
2. **Service Factory Layer** - Low-level API for creating JAX-WS services
3. **Metadata Layer** - Enumerations for service/port metadata (QNames, WSDL URLs, classes)

## Port Factory API

The Port Factory API is the recommended high-level interface for creating SOAP port instances.

### PortFactoryEnum

Central enumeration for creating SOAP ports for all Salesforce APIs.

**Package:** `org.solenopsis.soap.port.factory`

**Enum Constants:**

| Constant | Port Type | Description |
|----------|-----------|-------------|
| `APEX` | `ApexPortType` | Execute anonymous Apex code and manage logs |
| `ENTERPRISE` | `Soap` | Strongly-typed data API for known schemas |
| `METADATA` | `MetadataPortType` | Deploy and retrieve metadata |
| `PARTNER` | `Soap` | Dynamically-typed data API for any org |
| `TOOLING` | `SforceServicePortType` | Developer tools and schema introspection |

**Methods:**

#### createPort()

Creates a SOAP port instance with the default endpoint.

```java
public <T> T createPort()
```

**Returns:** A new SOAP port instance

**Type Parameters:**
- `T` - The port type (must match the enum constant)

**Example:**
```java
MetadataPortType port = PortFactoryEnum.METADATA.createPort();
```

**Warning:** Uses unchecked cast. Callers must ensure correct enum constant for desired port type to avoid `ClassCastException`.

#### createPort(String url)

Creates a SOAP port instance configured with a specific endpoint URL.

```java
public <T> T createPort(String url)
```

**Parameters:**
- `url` - The SOAP endpoint URL (e.g., `https://na1.salesforce.com/services/Soap/m/58.0`)

**Returns:** A new SOAP port instance configured with the specified URL

**Throws:**
- `IllegalArgumentException` - if URL is null, empty, blank, or malformed

**Example:**
```java
MetadataPortType port = PortFactoryEnum.METADATA.createPort(
    "https://na1.salesforce.com/services/Soap/m/58.0"
);
```

**Valid URL Formats:**
- Production: `https://login.salesforce.com/services/Soap/{api}/{version}`
- Sandbox: `https://test.salesforce.com/services/Soap/{api}/{version}`
- Custom Domain: `https://mydomain.my.salesforce.com/services/Soap/{api}/{version}`

Where `{api}` is:
- `s` for Enterprise/Partner APIs
- `m` for Metadata API
- `T` for Tooling API
- No letter for Apex API

#### getPortFactory()

Returns the underlying port factory implementation.

```java
public <T> PortFactory<T> getPortFactory()
```

**Returns:** The PortFactory for creating port instances

**Warning:** Uses unchecked cast. Type safety is caller's responsibility.

### PortFactory Interface

Base interface for all port factory implementations.

**Package:** `org.solenopsis.soap.port.factory`

```java
public interface PortFactory<T> extends Supplier<T>
```

**Type Parameters:**
- `T` - The type of SOAP port this factory creates

**Methods:**
- Inherits `get()` from `Supplier<T>` - Creates a new port instance

**Implementations:**
- `ApexPortFactory` - Creates `ApexPortType` instances
- `EnterprisePortFactory` - Creates `Soap` instances (Enterprise API)
- `MetadataPortFactory` - Creates `MetadataPortType` instances
- `PartnerPortFactory` - Creates `Soap` instances (Partner API)
- `ToolingPortFactory` - Creates `SforceServicePortType` instances

## Service Factory API

The Service Factory API provides low-level access to JAX-WS Service instances.

### ServiceFactoryEnum

Enumeration for creating JAX-WS Service instances for all Salesforce APIs.

**Package:** `org.solenopsis.soap.service.factory`

**Enum Constants:**

| Constant | Service Type | Description |
|----------|--------------|-------------|
| `APEX` | `ApexService` | Apex API service |
| `ENTERPRISE` | `SforceService` | Enterprise API service |
| `METADATA` | `MetadataService` | Metadata API service |
| `PARTNER` | `SforceService` | Partner API service |
| `TOOLING` | `SforceServiceService` | Tooling API service |

**Methods:**

#### createService()

Creates a JAX-WS Service instance.

```java
public Service createService()
```

**Returns:** A cached singleton Service instance

**Performance Note:** Services are cached using thread-safe lazy initialization (double-checked locking). The same instance is reused for all calls to avoid repeated WSDL parsing.

**Example:**
```java
MetadataService service = (MetadataService) ServiceFactoryEnum.METADATA.createService();
MetadataPortType port = service.getMetadata();
```

#### getServiceFactory()

Returns the underlying service factory implementation.

```java
public ServiceFactory<?> getServiceFactory()
```

**Returns:** The ServiceFactory for creating service instances

### ServiceFactory Interface

Base interface for all service factory implementations.

**Package:** `org.solenopsis.soap.service.factory`

```java
public interface ServiceFactory<T extends Service> extends Supplier<T>
```

**Type Parameters:**
- `T` - The type of JAX-WS Service this factory creates

**Methods:**
- Inherits `get()` from `Supplier<T>` - Creates/returns cached service instance

**Implementations:**
- `ApexServiceFactory` - Creates cached `ApexService` instances
- `EnterpriseServiceFactory` - Creates cached `SforceService` instances (Enterprise)
- `MetadataServiceFactory` - Creates cached `MetadataService` instances
- `PartnerServiceFactory` - Creates cached `SforceService` instances (Partner)
- `ToolingServiceFactory` - Creates cached `SforceServiceService` instances

**Caching Behavior:**

All service factories implement singleton pattern with thread-safe lazy initialization:

```java
private volatile ServiceType instance;

@Override
public ServiceType get() {
    ServiceType result = instance;
    if (result == null) {
        synchronized (this) {
            result = instance;
            if (result == null) {
                instance = result = new ServiceType(wsdlUrl);
            }
        }
    }
    return result;
}
```

## Service Metadata API

Enumerations that provide access to service metadata.

### ServiceEnum

Aggregates all service metadata (QName, WSDL, Factory) for each Salesforce API.

**Package:** `org.solenopsis.soap.service`

**Enum Constants:**

| Constant | Description |
|----------|-------------|
| `APEX` | Apex API service aggregation |
| `ENTERPRISE` | Enterprise API service aggregation |
| `METADATA` | Metadata API service aggregation |
| `PARTNER` | Partner API service aggregation |
| `TOOLING` | Tooling API service aggregation |

**Methods:**

#### getQName()

```java
public ServiceQNameEnum getQName()
```

**Returns:** The ServiceQNameEnum containing the service's qualified name

#### getWsdl()

```java
public ServiceWsdlEnum getWsdl()
```

**Returns:** The ServiceWsdlEnum containing the WSDL file URL

#### getFactory()

```java
public ServiceFactoryEnum getFactory()
```

**Returns:** The ServiceFactoryEnum for creating this service

**Example:**
```java
ServiceEnum service = ServiceEnum.METADATA;
QName qname = service.getQName().getQName();
URL wsdl = service.getWsdl().getUrl();
Service svc = service.getFactory().createService();
```

### ServiceQNameEnum

Provides QName (Qualified Name) for each Salesforce service.

**Package:** `org.solenopsis.soap.service`

**Enum Constants:**

| Constant | Service Class |
|----------|---------------|
| `APEX` | `ApexService.class` |
| `ENTERPRISE` | `SforceService.class` (Enterprise) |
| `METADATA` | `MetadataService.class` |
| `PARTNER` | `org.solenopsis.soap.partner.SforceService.class` |
| `TOOLING` | `SforceServiceService.class` |

**Methods:**

#### getQName()

```java
public QName getQName()
```

**Returns:** The QName for this service (validated non-null at build time)

**Throws:**
- `IllegalStateException` - if QName could not be computed

**Example:**
```java
QName qname = ServiceQNameEnum.METADATA.getQName();
// Typical QName: {http://soap.sforce.com/2006/04/metadata}MetadataService
```

**Implementation:** Uses `SoapUtil.computeQName(Class)` to extract namespace and local name from WSDL annotations at initialization time.

### ServiceWsdlEnum

Provides URLs to WSDL files on the classpath.

**Package:** `org.solenopsis.soap.service`

**Enum Constants:**

| Constant | WSDL Resource Path |
|----------|-------------------|
| `APEX` | `wsdl/soap-apex.wsdl` |
| `ENTERPRISE` | `wsdl/soap-enterprise.wsdl` |
| `METADATA` | `wsdl/soap-metadata.wsdl` |
| `PARTNER` | `wsdl/soap-partner.wsdl` |
| `TOOLING` | `wsdl/soap-tooling.wsdl` |

**Methods:**

#### getUrl()

```java
public URL getUrl()
```

**Returns:** The URL to the WSDL file (never null)

**Throws:**
- `IllegalStateException` - if WSDL resource not found on classpath

**Example:**
```java
URL wsdlUrl = ServiceWsdlEnum.METADATA.getUrl();
MetadataService service = new MetadataService(wsdlUrl);
```

**WSDL Location:** All WSDL files are located in `src/main/resources/wsdl/` and packaged in the JAR.

## Port Metadata API

Enumerations that provide access to port metadata.

### PortClassEnum

Maps service types to their SOAP port interface classes.

**Package:** `org.solenopsis.soap.port`

**Enum Constants:**

| Constant | Port Class | Description |
|----------|------------|-------------|
| `APEX` | `ApexPortType.class` | Apex API port interface |
| `ENTERPRISE` | `Soap.class` (enterprise) | Enterprise API port interface |
| `PARTNER` | `Soap.class` (partner) | Partner API port interface |
| `METADATA` | `MetadataPortType.class` | Metadata API port interface |
| `TOOLING` | `SforceServicePortType.class` | Tooling API port interface |

**Methods:**

#### getPortType()

```java
public Class<?> getPortType()
```

**Returns:** The Class object representing the SOAP port interface

**Example:**
```java
Class<?> portClass = PortClassEnum.METADATA.getPortType();
// Returns: org.solenopsis.soap.metadata.MetadataPortType.class
```

### PortMethodEnum

Provides the port creation method (annotated with `@WebEndpoint`) for each service.

**Package:** `org.solenopsis.soap.port`

**Enum Constants:**

| Constant | Service Class |
|----------|---------------|
| `APEX` | `ApexService.class` |
| `ENTERPRISE` | `SforceService.class` (Enterprise) |
| `METADATA` | `MetadataService.class` |
| `PARTNER` | `org.solenopsis.soap.partner.SforceService.class` |
| `TOOLING` | `SforceServiceService.class` |

**Methods:**

#### getPortMethod()

```java
public Method getPortMethod()
```

**Returns:** The Method for creating a port instance (annotated with `@WebEndpoint`, takes no parameters)

**Example:**
```java
Method method = PortMethodEnum.METADATA.getPortMethod();
// Returns method: MetadataService.getMetadata()
```

**Implementation:** Automatically discovers the parameterless method annotated with `@WebEndpoint` from the service class at initialization time.

## Supported Salesforce APIs

### Apex API

**Purpose:** Execute anonymous Apex code and manage debug logs

**Port Type:** `org.solenopsis.soap.apex.ApexPortType`

**Service Type:** `org.solenopsis.soap.apex.ApexService`

**Common Operations:**
- `compileAndTest()` - Compile and test Apex code
- `compileClasses()` - Compile Apex classes
- `compileTriggers()` - Compile Apex triggers
- `executeAnonymous()` - Execute anonymous Apex code

**Example:**
```java
ApexPortType apex = PortFactoryEnum.APEX.createPort(url);
ExecuteAnonymousResult result = apex.executeAnonymous("System.debug('Hello');");
```

### Enterprise API

**Purpose:** Strongly-typed data operations for known organization schemas

**Port Type:** `org.solenopsis.soap.enterprise.Soap`

**Service Type:** `org.solenopsis.soap.enterprise.SforceService`

**Common Operations:**
- `login()` - Authenticate and get session
- `query()` - Execute SOQL queries
- `create()` - Create records
- `update()` - Update records
- `delete()` - Delete records
- `upsert()` - Upsert records

**Example:**
```java
Soap enterprise = PortFactoryEnum.ENTERPRISE.createPort(url);
LoginResult login = enterprise.login(username, password);
```

**Use When:** Organization schema is known at development time and strong typing is desired.

### Metadata API

**Purpose:** Deploy, retrieve, and manage organization metadata

**Port Type:** `org.solenopsis.soap.metadata.MetadataPortType`

**Service Type:** `org.solenopsis.soap.metadata.MetadataService`

**Common Operations:**
- `deploy()` - Deploy metadata to organization
- `retrieve()` - Retrieve metadata from organization
- `create()` - Create metadata components
- `update()` - Update metadata components
- `delete()` - Delete metadata components
- `listMetadata()` - List metadata of specific type

**Example:**
```java
MetadataPortType metadata = PortFactoryEnum.METADATA.createPort(url);
DeployResult result = metadata.deploy(zipFile, deployOptions);
```

**Metadata Types:** Custom Objects, Apex Classes, Visualforce Pages, Flows, Custom Fields, etc.

### Partner API

**Purpose:** Dynamically-typed data operations for any organization

**Port Type:** `org.solenopsis.soap.partner.Soap`

**Service Type:** `org.solenopsis.soap.partner.SforceService`

**Common Operations:**
- `login()` - Authenticate and get session
- `query()` - Execute SOQL queries
- `describeSObjects()` - Get object metadata
- `create()` - Create records
- `update()` - Update records
- `delete()` - Delete records

**Example:**
```java
Soap partner = PortFactoryEnum.PARTNER.createPort(url);
LoginResult login = partner.login(username, password);
DescribeSObjectResult[] describes = partner.describeSObjects(new String[]{"Account"});
```

**Use When:** Building generic tools that work with any organization without knowing schema at development time.

**Difference from Enterprise API:** Partner API uses dynamic typing (generic SObject) while Enterprise API uses strongly-typed classes generated from organization-specific WSDL.

### Tooling API

**Purpose:** Developer tools, schema introspection, and metadata operations

**Port Type:** `org.solenopsis.soap.tooling.SforceServicePortType`

**Service Type:** `org.solenopsis.soap.tooling.SforceServiceService`

**Common Operations:**
- `query()` - Query tooling objects (ApexClass, ApexTrigger, etc.)
- `create()` - Create tooling objects
- `update()` - Update tooling objects
- `delete()` - Delete tooling objects
- `executeAnonymous()` - Execute anonymous Apex
- `runTests()` - Run Apex tests

**Example:**
```java
SforceServicePortType tooling = PortFactoryEnum.TOOLING.createPort(url);
QueryResult result = tooling.query("SELECT Id, Name FROM ApexClass");
```

**Tooling Objects:** ApexClass, ApexTrigger, ApexLog, MetadataContainer, SymbolTable, etc.

## Error Handling

All factory methods may throw standard JAX-WS exceptions:

### Common Exceptions

**IllegalArgumentException**
- Thrown by `createPort(String url)` if URL is invalid
- Thrown by `ServiceQNameEnum.getQName()` if QName cannot be computed
- Thrown by `ServiceWsdlEnum.getUrl()` if WSDL resource not found

**ClassCastException**
- Thrown by `createPort()` if generic type doesn't match enum constant's actual type

**NullPointerException**
- Thrown by `PortMethodEnum` constructor if port method not found

**WebServiceException** (from JAX-WS)
- Thrown during SOAP communication failures
- Check `getCause()` for underlying network/HTTP errors

### Best Practices

1. **Always catch exceptions when making SOAP calls:**
```java
try {
    MetadataPortType port = PortFactoryEnum.METADATA.createPort(url);
    DeployResult result = port.deploy(zip, options);
} catch (WebServiceException e) {
    // Handle SOAP communication errors
    logger.error("SOAP call failed", e);
}
```

2. **Validate URLs before creating ports:**
```java
String url = "https://na1.salesforce.com/services/Soap/m/58.0";
try {
    new URL(url); // Validate format
    MetadataPortType port = PortFactoryEnum.METADATA.createPort(url);
} catch (MalformedURLException e) {
    throw new IllegalArgumentException("Invalid URL: " + url, e);
}
```

3. **Use correct enum constant for type safety:**
```java
// CORRECT
MetadataPortType port = PortFactoryEnum.METADATA.createPort();

// WRONG - ClassCastException at runtime
Soap port = PortFactoryEnum.METADATA.createPort();
```

## Thread Safety

### Thread-Safe Components

**ServiceFactory implementations:**
- All service factories use thread-safe double-checked locking
- Singleton instances are safely published via `volatile` keyword
- Safe for concurrent access from multiple threads

**Enum constants:**
- All enum classes are inherently thread-safe
- Metadata is computed once during class initialization
- Safe for concurrent access

### Not Thread-Safe Components

**Port instances:**
- Port instances are NOT thread-safe
- Each thread should create its own port instance
- Do NOT share port instances across threads

**Service instances:**
- Service instances are thread-safe for read operations
- Port creation from services is thread-safe
- Cached singleton services can be safely shared

### Recommended Patterns

**Multi-threaded port creation:**
```java
ExecutorService executor = Executors.newFixedThreadPool(10);
for (int i = 0; i < 100; i++) {
    executor.submit(() -> {
        // Each thread creates its own port
        MetadataPortType port = PortFactoryEnum.METADATA.createPort(url);
        // Use port...
    });
}
```

**Sharing service, creating ports per thread:**
```java
// Shared cached service - thread-safe
MetadataService service = (MetadataService) ServiceFactoryEnum.METADATA.createService();

ExecutorService executor = Executors.newFixedThreadPool(10);
for (int i = 0; i < 100; i++) {
    executor.submit(() -> {
        // Each thread creates its own port from shared service
        MetadataPortType port = service.getMetadata();
        // Use port...
    });
}
```

## Performance Considerations

### Service Caching

Services are expensive to create (WSDL parsing ~6.6MB total). The library caches service instances:

- **First call:** Parses WSDL, creates service (~100-200ms)
- **Subsequent calls:** Returns cached instance (~1-2ms)

**Implication:** First `createPort()` or `createService()` call per API type is slower.

### Port Creation

Ports are lightweight to create from cached services:

- **Typical creation time:** 1-5ms per port
- **No WSDL parsing:** Uses cached service

**Recommendation:** Create ports as needed, don't pool them excessively.

### Memory Usage

**Service instances:**
- ~5 service classes, each ~1-2MB in memory
- Total memory: ~5-10MB for all cached services
- Services held indefinitely (singleton pattern)

**Port instances:**
- Lightweight, ~1-10KB per port
- Create as needed, let GC reclaim when done

### WSDL File Sizes

| WSDL File | Size |
|-----------|------|
| soap-apex.wsdl | ~500KB |
| soap-enterprise.wsdl | ~2MB |
| soap-metadata.wsdl | ~1.5MB |
| soap-partner.wsdl | ~1.2MB |
| soap-tooling.wsdl | ~1.4MB |
| **Total** | **~6.6MB** |

## Version Compatibility

### Java Version

**Minimum:** Java 17

**Tested:** Java 17, 21

### Salesforce API Version

WSDL files define the supported API version. To update:

1. Download new WSDL files from Salesforce
2. Replace files in `src/main/resources/wsdl/`
3. Rebuild: `mvn clean install`

### Dependencies

**Required Runtime:**
- Apache CXF 4.0+
- FlossWare JCommons 1.10+
- Apache Commons Lang3 3.18+

See `pom.xml` for exact versions.

## Related Documentation

- [README.md](README.md) - Getting started and overview
- [ARCHITECTURE.md](ARCHITECTURE.md) - Design decisions and patterns
- [CHANGELOG.md](CHANGELOG.md) - Version history
- [Javadoc](target/site/apidocs/) - Generated API documentation

## Salesforce Documentation

- [Apex API Developer Guide](https://developer.salesforce.com/docs/atlas.en-us.apexcode.meta/apexcode/)
- [Enterprise/Partner WSDL and API Guide](https://developer.salesforce.com/docs/atlas.en-us.api.meta/api/)
- [Metadata API Developer Guide](https://developer.salesforce.com/docs/atlas.en-us.api_meta.meta/api_meta/)
- [Tooling API Developer Guide](https://developer.salesforce.com/docs/atlas.en-us.api_tooling.meta/api_tooling/)
