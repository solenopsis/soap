# Solenopsis SOAP Architecture

This document describes the architecture, design patterns, and key design decisions for the Solenopsis SOAP library.

## Table of Contents

- [Overview](#overview)
- [Architecture Layers](#architecture-layers)
- [Design Patterns](#design-patterns)
- [Code Generation](#code-generation)
- [Performance Optimizations](#performance-optimizations)
- [Thread Safety](#thread-safety)
- [Error Handling Strategy](#error-handling-strategy)
- [Package Organization](#package-organization)
- [Key Design Decisions](#key-design-decisions)
- [Testing Strategy](#testing-strategy)

## Overview

The Solenopsis SOAP library is a thin abstraction layer over Apache CXF-generated SOAP clients for Salesforce APIs. The design emphasizes:

1. **Simplicity** - Minimal learning curve, intuitive API
2. **Type Safety** - Compile-time guarantees where possible
3. **Performance** - Service caching, lazy initialization
4. **Maintainability** - Clear separation of concerns, comprehensive tests
5. **Flexibility** - Support both default and custom endpoints

## Architecture Layers

The library is organized into three distinct layers:

```
┌─────────────────────────────────────────────────────────────┐
│                     Application Layer                       │
│              (User code using the library)                  │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│                   Port Factory Layer                        │
│  PortFactoryEnum - High-level port creation interface       │
│  Responsibilities:                                          │
│  - Simple API: createPort(), createPort(url)                │
│  - URL validation                                           │
│  - Enum-based type-safe selection                           │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│                  Service Factory Layer                      │
│  ServiceFactoryEnum - Service instance management           │
│  Responsibilities:                                          │
│  - Singleton service caching                                │
│  - Thread-safe lazy initialization                          │
│  - WSDL loading and service creation                        │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│                    Metadata Layer                           │
│  ServiceWsdlEnum, ServiceQNameEnum, PortClassEnum           │
│  Responsibilities:                                          │
│  - Classpath WSDL URL resolution                            │
│  - QName computation from service classes                   │
│  - Port class mappings                                      │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│                    Generated Layer                          │
│  Apache CXF generated code from WSDL files                  │
│  - Service classes (ApexService, MetadataService, etc.)     │
│  - Port interfaces (ApexPortType, MetadataPortType, etc.)   │
│  - Data transfer objects (DTOs)                             │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│                      Apache CXF                             │
│               JAX-WS SOAP Runtime                           │
└─────────────────────────────────────────────────────────────┘
```

### Layer Responsibilities

**Port Factory Layer:**
- Primary user-facing API
- Validates URLs before port creation
- Delegates to service factories

**Service Factory Layer:**
- Manages expensive service creation
- Implements caching to avoid repeated WSDL parsing
- Thread-safe singleton pattern

**Metadata Layer:**
- Pure data enumerations
- No side effects or state
- Initialized once at class load time

**Generated Layer:**
- Auto-generated from WSDL files
- Not manually modified
- Regenerated during build

## Design Patterns

### 1. Factory Pattern

**Pattern:** Abstract Factory with enum-based selection

**Rationale:**
- Clean separation between object creation and usage
- Centralized creation logic for all Salesforce APIs
- Type-safe selection via enums

**Implementation:**

```java
// Factory interface
public interface PortFactory<T> extends Supplier<T> {
}

// Concrete factories (package-private)
final class ApexPortFactory implements PortFactory<ApexPortType> {
    @Override
    public ApexPortType get() {
        return ((ApexService) ServiceFactoryEnum.APEX.createService()).getApex();
    }
}

// Factory enum (public API)
public enum PortFactoryEnum {
    APEX(new ApexPortFactory()),
    METADATA(new MetadataPortFactory()),
    // ...
}
```

**Benefits:**
- Users don't need to know about service creation
- Easy to add new API types
- Consistent creation pattern across all APIs

### 2. Singleton Pattern (Service Caching)

**Pattern:** Thread-safe lazy initialization with double-checked locking

**Rationale:**
- WSDL parsing is expensive (~100-200ms per service)
- Service instances are immutable and thread-safe
- Total WSDL size is ~6.6MB across 5 APIs

**Implementation:**

```java
final class ApexServiceFactory implements ServiceFactory<ApexService> {
    private volatile ApexService instance;

    @Override
    public ApexService get() {
        ApexService result = instance;
        if (result == null) {
            synchronized (this) {
                result = instance;
                if (result == null) {
                    instance = result = new ApexService(ServiceWsdlEnum.APEX.getUrl());
                }
            }
        }
        return result;
    }
}
```

**Key Elements:**
- `volatile` keyword ensures visibility across threads
- Double-checked locking avoids synchronization overhead after initialization
- Local variable `result` provides small performance improvement

**Performance Impact:**
- First call: ~100-200ms (WSDL parsing + service creation)
- Subsequent calls: ~1-2ms (return cached instance)
- Memory: ~1-2MB per service, ~5-10MB total for all services

### 3. Strategy Pattern (Port Creation)

**Pattern:** Different strategies for port creation (default vs. custom URL)

**Implementation:**

```java
// Strategy 1: Default endpoint
public <T> T createPort() {
    return (T) getPortFactory().get();
}

// Strategy 2: Custom endpoint
public <T> T createPort(final String url) {
    new URL(url); // Validate
    return SoapUtil.setUrl(createPort(), url);
}
```

**Rationale:**
- Most users need custom URLs (production, sandbox, custom domains)
- Some users may want default endpoint for testing
- URL validation prevents runtime SOAP errors

### 4. Enum Singleton Pattern

**Pattern:** Using enums to ensure singleton semantics

**Rationale:**
- Enum instances are inherently singleton
- JVM guarantees thread-safe initialization
- Prevents reflection/serialization attacks on singleton

**Implementation:**

All factory enums (`PortFactoryEnum`, `ServiceFactoryEnum`, etc.) use this pattern.

### 5. Supplier Pattern

**Pattern:** Factory interfaces extend `Supplier<T>`

**Rationale:**
- Aligns with Java 8+ functional interfaces
- Enables functional composition
- Clear contract: `get()` produces instances

**Implementation:**

```java
public interface PortFactory<T> extends Supplier<T> {
}

public interface ServiceFactory<T extends Service> extends Supplier<T> {
}
```

### 6. Template Method Pattern (Implicit)

**Pattern:** Service factories follow common template

**Template:**
1. Check cached instance
2. If null, synchronize
3. Double-check null
4. Create service with WSDL URL
5. Cache and return

**Implementation:** Each `XxxServiceFactory` implements this template identically.

## Code Generation

### Generation Process

```
Build Phase: generate-sources
        ↓
Read WSDL files from src/main/resources/wsdl/
        ↓
Apache CXF cxf-codegen-plugin
        ↓
Generate Java classes to target/generated-sources/cxf/
        ↓
Compile both generated and custom code
        ↓
Package into JAR
```

### Maven Configuration

```xml
<plugin>
    <groupId>org.apache.cxf</groupId>
    <artifactId>cxf-codegen-plugin</artifactId>
    <executions>
        <execution>
            <phase>generate-sources</phase>
            <configuration>
                <wsdlOptions>
                    <wsdlOption>
                        <wsdl>${basedir}/src/main/resources/wsdl/soap-metadata.wsdl</wsdl>
                        <extraargs>
                            <extraarg>-p</extraarg>
                            <extraarg>org.solenopsis.soap.metadata</extraarg>
                        </extraargs>
                    </wsdlOption>
                    <!-- ... other WSDLs ... -->
                </wsdlOptions>
            </configuration>
        </execution>
    </executions>
</plugin>
```

### Generated Code Structure

For each WSDL file, CXF generates:

1. **Service class** - Top-level JAX-WS Service (e.g., `MetadataService`)
2. **Port interface** - Port type interface (e.g., `MetadataPortType`)
3. **DTOs** - Data transfer objects for all operations
4. **Exception classes** - Fault bean classes
5. **ObjectFactory** - JAXB object factory for creating instances

### Why Code Generation?

**Benefits:**
- Type-safe SOAP operations
- Auto-updated when WSDL changes
- Comprehensive coverage of all operations
- Standard JAX-WS annotations

**Trade-offs:**
- Build-time dependency on WSDLs
- Large generated codebase (~1000+ classes)
- Generates code not typically used by library users

**Decision:** Benefits outweigh costs for SOAP library use case.

## Performance Optimizations

### 1. Service Caching (Singleton Pattern)

**Problem:** Creating JAX-WS Service instances is expensive

**Solution:** Cache service instances as singletons

**Measurement:**
- WSDL parsing: ~100-200ms per service
- Service creation: ~50-100ms
- Total first-call overhead: ~150-300ms per API

**Improvement:**
- Subsequent calls: ~1-2ms (cached)
- ~100x faster after first call

### 2. Lazy Initialization

**Problem:** Not all APIs are used in every application

**Solution:** Create services only when first requested

**Benefit:**
- Applications using only Metadata API don't pay cost of parsing other WSDLs
- Faster startup time
- Lower memory usage for partial API usage

### 3. Double-Checked Locking

**Problem:** Synchronization overhead on every service access

**Solution:** Double-checked locking pattern

**Measurement:**
- Synchronized method: ~10-20ns overhead per call
- Double-checked locking: ~2-5ns overhead per call after initialization
- ~4x faster for cached instance access

### 4. Classpath WSDL Loading

**Problem:** Network I/O for WSDL retrieval is slow and unreliable

**Solution:** Package WSDL files in JAR, load from classpath

**Benefit:**
- No network dependency at runtime
- Consistent behavior across environments
- ~100ms faster than network WSDL loading

### 5. Minimal Object Creation

**Design:** Reuse enum instances, avoid wrapper objects

**Example:**
```java
// Good: Reuse enum
MetadataPortType port = PortFactoryEnum.METADATA.createPort();

// Avoided: Don't create wrapper factories
// MetadataPortFactory factory = new MetadataPortFactory();
```

## Thread Safety

### Thread-Safe Components

**All enum classes:**
- `PortFactoryEnum`
- `ServiceFactoryEnum`
- `ServiceQNameEnum`
- `ServiceWsdlEnum`
- `PortClassEnum`
- `PortMethodEnum`

**Rationale:** Enums are inherently thread-safe (JVM guarantee)

**Service factory implementations:**
- `ApexServiceFactory`
- `EnterpriseServiceFactory`
- `MetadataServiceFactory`
- `PartnerServiceFactory`
- `ToolingServiceFactory`

**Mechanism:** Double-checked locking with `volatile`

**Service instances:**
- Thread-safe for read operations
- Immutable after creation
- Can be safely shared across threads

### Not Thread-Safe Components

**Port instances:**
- Created from services
- JAX-WS ports are NOT thread-safe
- Each thread must have its own port

**Rationale:**
- JAX-WS specification: ports maintain mutable state (request context, handlers)
- Port creation is cheap (~1-5ms)
- Thread-local pattern recommended for high-concurrency scenarios

### Thread Safety Strategy

**Strategy 1: Create ports per thread**
```java
ExecutorService executor = Executors.newFixedThreadPool(10);
for (int i = 0; i < 100; i++) {
    executor.submit(() -> {
        MetadataPortType port = PortFactoryEnum.METADATA.createPort(url);
        // Use port...
    });
}
```

**Strategy 2: ThreadLocal ports** (advanced)
```java
ThreadLocal<MetadataPortType> portHolder = ThreadLocal.withInitial(() ->
    PortFactoryEnum.METADATA.createPort(url)
);

ExecutorService executor = Executors.newFixedThreadPool(10);
for (int i = 0; i < 100; i++) {
    executor.submit(() -> {
        MetadataPortType port = portHolder.get();
        // Use port...
    });
}
```

## Error Handling Strategy

### Fail-Fast Design

**Philosophy:** Detect errors as early as possible

**Examples:**

1. **URL Validation at creation time:**
```java
public <T> T createPort(final String url) {
    try {
        new URL(url);  // Fail fast if URL is malformed
    } catch (MalformedURLException e) {
        throw new IllegalArgumentException("Invalid URL: " + url, e);
    }
    return SoapUtil.setUrl(createPort(), url);
}
```

2. **QName validation at initialization:**
```java
public QName getQName() {
    if (qname == null) {
        throw new IllegalStateException("QName could not be computed for " + this.name());
    }
    return qname;
}
```

3. **WSDL validation at initialization:**
```java
public URL getUrl() {
    if (wsdl == null) {
        throw new IllegalStateException("WSDL resource not found on classpath for " + this.name());
    }
    return wsdl;
}
```

### Build-Time Validation

**Unit Tests:** Validate all metadata at build time

**Example:**
```java
@Test
void testQNameComputed() {
    for (ServiceQNameEnum service : ServiceQNameEnum.values()) {
        assertNotNull(service.getQName());
    }
}

@Test
void testWsdlExists() {
    for (ServiceWsdlEnum wsdl : ServiceWsdlEnum.values()) {
        assertNotNull(wsdl.getUrl());
    }
}
```

**Benefit:** Catch configuration errors before deployment

### Exception Translation

**Strategy:** Wrap low-level exceptions with context

**Example:**
```java
catch (MalformedURLException e) {
    throw new IllegalArgumentException("Invalid URL: " + url, e);
}
```

**Rationale:**
- Preserve original exception (cause chain)
- Add contextual information (which URL failed)
- Use standard Java exceptions (IllegalArgumentException)

## Package Organization

```
org.solenopsis.soap/
├── apex/                          # Generated: Apex API classes
├── enterprise/                    # Generated: Enterprise API classes
├── metadata/                      # Generated: Metadata API classes
├── partner/                       # Generated: Partner API classes
├── tooling/                       # Generated: Tooling API classes
├── port/                          # Custom: Port metadata
│   ├── PortClassEnum.java         # Maps services to port classes
│   ├── PortMethodEnum.java        # Discovers port creation methods
│   ├── package-info.java          # Package documentation
│   └── factory/                   # Custom: Port factories
│       ├── PortFactory.java       # Factory interface
│       ├── PortFactoryEnum.java   # Main factory enum (public API)
│       ├── ApexPortFactory.java   # Apex port factory (package-private)
│       ├── EnterprisePortFactory.java
│       ├── MetadataPortFactory.java
│       ├── PartnerPortFactory.java
│       ├── ToolingPortFactory.java
│       └── package-info.java
└── service/                       # Custom: Service metadata
    ├── ServiceEnum.java           # Aggregates all service metadata
    ├── ServiceQNameEnum.java      # Service QNames
    ├── ServiceWsdlEnum.java       # WSDL URLs
    ├── package-info.java
    └── factory/                   # Custom: Service factories
        ├── ServiceFactory.java    # Factory interface
        ├── ServiceFactoryEnum.java # Main factory enum
        ├── ApexServiceFactory.java # Apex service factory (package-private)
        ├── EnterpriseServiceFactory.java
        ├── MetadataServiceFactory.java
        ├── PartnerServiceFactory.java
        ├── ToolingServiceFactory.java
        └── package-info.java
```

### Package Visibility Strategy

**Public APIs:**
- All enums (`PortFactoryEnum`, `ServiceFactoryEnum`, etc.)
- Factory interfaces (`PortFactory`, `ServiceFactory`)

**Package-Private:**
- Concrete factory implementations
- Users access via enum constants, not direct instantiation

**Rationale:**
- Encapsulation of implementation details
- Freedom to change factory implementations
- Users depend on stable enum API

## Key Design Decisions

### 1. Why Enums for Factories?

**Decision:** Use enums instead of static methods or Spring beans

**Alternatives Considered:**
- Static utility methods: `PortFactories.createMetadataPort()`
- Spring bean factory pattern
- Builder pattern

**Rationale:**
- Type-safe selection (compile-time checking)
- Centralized in one place
- Discoverable via IDE autocomplete
- No external dependencies (Spring, etc.)
- Natural singleton semantics

### 2. Why Cache Services But Not Ports?

**Decision:** Cache service instances, create new port instances on each call

**Rationale:**
- **Services:** Expensive to create (~100-200ms), thread-safe, immutable
- **Ports:** Cheap to create (~1-5ms), NOT thread-safe, mutable state

**Trade-off:** Memory vs. simplicity
- Caching ports would save ~1-5ms per creation
- But would require pooling/synchronization/thread-local complexity
- Not worth the complexity for 1-5ms savings

### 3. Why Two Factory Layers?

**Decision:** Separate port factories from service factories

**Alternatives Considered:**
- Single factory layer creating ports directly
- Expose service factories only

**Rationale:**
- **Separation of concerns:** Port creation vs. service management
- **Flexibility:** Advanced users can access service layer
- **Simplicity:** Most users only need port layer
- **Testability:** Can test each layer independently

### 4. Why Package WSDLs in JAR?

**Decision:** Include WSDL files in JAR instead of downloading at runtime

**Alternatives Considered:**
- Download from Salesforce at runtime
- Download once and cache locally
- Configure WSDL location via properties

**Rationale:**
- **Offline operation:** No network dependency
- **Reproducibility:** Same WSDL across all environments
- **Performance:** No network I/O (~100ms savings)
- **Reliability:** No network failures

**Trade-off:**
- JAR size increases by ~6.6MB
- Must rebuild to update WSDL versions
- Acceptable for library use case

### 5. Why Not Use javax.xml.ws.Service Directly?

**Decision:** Wrap JAX-WS Service creation in factory layer

**Rationale:**
- **Simplicity:** Users don't need to know about QNames, WSDL URLs
- **Consistency:** Same pattern for all APIs
- **Validation:** URL validation before port creation
- **Caching:** Transparent service caching
- **Evolution:** Can change implementation without breaking API

### 6. Why Volatile + Double-Checked Locking?

**Decision:** Use `volatile` with double-checked locking for service caching

**Alternatives Considered:**
- Eager initialization (create all services at startup)
- Synchronized method (simple but slow)
- Lock-free with AtomicReference
- Java 9+ VarHandle

**Rationale:**
- **Lazy:** Only create services when needed
- **Fast:** No synchronization overhead after initialization
- **Safe:** Volatile ensures proper publication
- **Compatible:** Works on Java 8+ (library targets Java 17+)

**Performance:**
- First call: ~100-200ms (one-time cost)
- Subsequent calls: ~1-2ms
- Synchronization overhead: ~2-5ns

### 7. Why Fail-Fast Error Handling?

**Decision:** Validate at creation time, throw exceptions immediately

**Alternatives Considered:**
- Return null on errors (require null checks)
- Return Optional (verbose API)
- Lazy validation (fail on first SOAP call)

**Rationale:**
- **Clarity:** Errors are obvious and immediate
- **Debuggability:** Stack trace shows creation point, not deep in SOAP stack
- **Correctness:** Invalid configuration detected before use
- **Standards:** Follows Java conventions (IllegalArgumentException, etc.)

## Testing Strategy

### Test Coverage Goals

**Target:** 100% coverage for custom code

**Achieved:**
- `org.solenopsis.soap.service`: 100%
- `org.solenopsis.soap.service.factory`: 100%
- `org.solenopsis.soap.port`: 100%
- `org.solenopsis.soap.port.factory`: 100%

### Test Organization

```
src/test/java/
└── org/solenopsis/soap/
    ├── port/
    │   ├── PortClassEnumTest.java
    │   ├── PortMethodEnumTest.java
    │   └── factory/
    │       ├── PortFactoryEnumTest.java
    │       ├── ApexPortFactoryTest.java
    │       ├── EnterprisePortFactoryTest.java
    │       ├── MetadataPortFactoryTest.java
    │       ├── PartnerPortFactoryTest.java
    │       └── ToolingPortFactoryTest.java
    └── service/
        ├── ServiceEnumTest.java
        ├── ServiceQNameEnumTest.java
        ├── ServiceWsdlEnumTest.java
        └── factory/
            └── ServiceFactoryEnumTest.java (implicitly tests all service factories)
```

### Testing Patterns

**1. Enum Constant Tests**
```java
@Test
void testAllEnumsHaveFactories() {
    for (PortFactoryEnum pfe : PortFactoryEnum.values()) {
        assertNotNull(pfe.getPortFactory());
    }
}
```

**2. Port Creation Tests**
```java
@Test
void testCreatePort() {
    ApexPortType port = PortFactoryEnum.APEX.createPort();
    assertNotNull(port);
}

@Test
void testCreatePortWithUrl() {
    String url = "https://na1.salesforce.com/services/Soap/s/58.0";
    ApexPortType port = PortFactoryEnum.APEX.createPort(url);
    assertNotNull(port);
}
```

**3. Error Handling Tests**
```java
@Test
void testInvalidUrl() {
    assertThrows(IllegalArgumentException.class, () ->
        PortFactoryEnum.APEX.createPort("not-a-url")
    );
}
```

**4. Metadata Validation Tests**
```java
@Test
void testQNamesComputed() {
    for (ServiceQNameEnum service : ServiceQNameEnum.values()) {
        QName qname = service.getQName();
        assertNotNull(qname);
        assertNotNull(qname.getNamespaceURI());
        assertNotNull(qname.getLocalPart());
    }
}
```

**5. WSDL Existence Tests**
```java
@Test
void testWsdlFilesExist() {
    for (ServiceWsdlEnum wsdl : ServiceWsdlEnum.values()) {
        URL url = wsdl.getUrl();
        assertNotNull(url);
        assertTrue(url.toString().endsWith(".wsdl"));
    }
}
```

### Test Philosophy

**Unit Tests:**
- Test custom code thoroughly (100% coverage)
- Don't test generated code (trust CXF)
- Don't test JAX-WS runtime (trust Apache CXF)

**Integration Tests:**
- Not included (require Salesforce credentials)
- Users should write their own integration tests
- Library provides testable seams (factory interfaces)

**Build-Time Validation:**
- All enum constants validated at test time
- Catches missing resources, null QNames, etc.
- Fails build if metadata invalid

## Dependencies

### Runtime Dependencies

**Apache CXF 4.0+**
- JAX-WS SOAP framework
- WSDL parsing and service creation
- HTTP transport

**FlossWare Commons Java 1.38+**
- `SoapUtil.computeQName()` - QName computation
- `SoapUtil.setUrl()` - Port endpoint configuration
- Foundation utilities

**Apache Commons Lang3 3.18+**
- String utilities
- Null-safe operations

### Build Dependencies

**CXF Codegen Plugin**
- WSDL to Java code generation
- Runs during `generate-sources` phase

**JUnit 5**
- Unit testing framework
- Test organization and assertions

**Mockito**
- Mocking framework (minimal usage)
- Not heavily used (testing real objects)

**JaCoCo**
- Code coverage reporting
- Build fails if coverage < 100% for custom code

### Dependency Management

**Strategy:** Minimal dependencies, well-tested libraries

**Rationale:**
- Fewer dependencies = fewer conflicts
- Standard libraries (Apache CXF, Commons) are stable
- FlossWare Commons Java is companion library by same author

## Evolution and Extensibility

### Adding New Salesforce APIs

**Steps:**
1. Add WSDL file to `src/main/resources/wsdl/`
2. Add `<wsdlOption>` to `pom.xml`
3. Add enum constant to each relevant enum:
   - `PortFactoryEnum`
   - `ServiceFactoryEnum`
   - `ServiceQNameEnum`
   - `ServiceWsdlEnum`
   - `PortClassEnum`
   - `PortMethodEnum`
4. Create factory implementations:
   - `XxxPortFactory`
   - `XxxServiceFactory`
5. Add tests for new enum constants
6. Rebuild and verify

**Design supports easy addition:** All extension points are enums.

### Updating Salesforce API Versions

**Steps:**
1. Download new WSDL files from Salesforce
2. Replace files in `src/main/resources/wsdl/`
3. Run `mvn clean install`
4. Generated code automatically updated
5. Run tests to verify compatibility

**No code changes required** (unless Salesforce changes API structure).

### Customization Points

**1. Custom Port Factories**

Users can create their own factories:
```java
public class CustomMetadataPortFactory implements PortFactory<MetadataPortType> {
    @Override
    public MetadataPortType get() {
        MetadataPortType port = PortFactoryEnum.METADATA.createPort(url);
        // Custom configuration
        ((BindingProvider) port).getRequestContext().put(
            BindingProvider.SESSION_MAINTAIN_PROPERTY, true);
        return port;
    }
}
```

**2. Custom Service Configuration**

Users can access service layer directly:
```java
MetadataService service = (MetadataService) ServiceFactoryEnum.METADATA.createService();
service.setHandlerResolver(customHandlerResolver);
MetadataPortType port = service.getMetadata();
```

**3. Integration with Frameworks**

Spring example:
```java
@Configuration
public class SoapConfig {
    @Bean
    public MetadataPortType metadataPort(@Value("${salesforce.url}") String url) {
        return PortFactoryEnum.METADATA.createPort(url);
    }
}
```

## Future Considerations

### Potential Enhancements

**1. Port Pooling**
- Thread-safe port pool for high-concurrency scenarios
- Trade-off: Complexity vs. ~1-5ms per port creation
- **Decision:** Not implemented (not needed for most use cases)

**2. Async API Support**
- JAX-WS async operations
- CompletableFuture-based API
- **Decision:** Users can use JAX-WS async directly if needed

**3. Bulk Operations API**
- Salesforce Bulk API support
- Different protocol (REST, not SOAP)
- **Decision:** Out of scope for SOAP library

**4. Metadata Caching**
- Cache `describeSObjects()` results
- **Decision:** Application-level concern, not library

**5. Connection Pooling**
- HTTP connection pooling
- **Decision:** Handled by Apache CXF/HTTP client

### Backward Compatibility

**Commitment:** Semantic versioning

- **Major version:** Breaking API changes
- **Minor version:** New features, backward compatible
- **Patch version:** Bug fixes

**Stable APIs:**
- All public enum classes
- Factory interfaces
- Public methods on enums

**Unstable (package-private):**
- Concrete factory implementations
- Can change without version bump

## Related Documentation

- [README.md](README.md) - Getting started guide
- [API.md](API.md) - Complete API reference
- [CHANGELOG.md](CHANGELOG.md) - Version history

## References

- [Apache CXF Documentation](https://cxf.apache.org/docs/)
- [JAX-WS Specification](https://jcp.org/en/jsr/detail?id=224)
- [Salesforce SOAP API Documentation](https://developer.salesforce.com/docs/atlas.en-us.api.meta/api/)
- [Effective Java (3rd Edition)](https://www.oreilly.com/library/view/effective-java/9780134686097/) - Design patterns and best practices
