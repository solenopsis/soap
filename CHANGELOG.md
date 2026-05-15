# Changelog

All notable changes to the Solenopsis SOAP library will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.11] - 2026-05-15

### Changed
- Updated FlossWare dependency from commons to jcommons
- Updated org.flossware:commons:1.10 → org.flossware:jcommons:1.14
- Updated Java imports from org.flossware.commons.* → org.flossware.jcommons.*

### Breaking Changes
- Requires FlossWare JCommons 1.14+ (renamed from Commons)

## [1.8] - 2024-09-17

### Changed
- Replaced all wildcard imports with explicit imports for better code clarity
- Updated FlossWare Commons dependency to 1.10
- All test files now use explicit static imports from JUnit assertions

### Added
- Comprehensive test suite with 25 unit tests
  - `PortFactoryEnumTest` - 11 tests for port creation
  - `ServiceEnumTest` - 7 tests for service enumeration
  - `ServiceQNameEnumTest` - 7 tests for QName handling
- Comprehensive README with architecture and usage examples
- CHANGELOG.md for version tracking
- Validation for port creation with URLs (null/empty/blank checks)

### Changed
- Updated Apache CXF to 4.0.9
- Updated Apache Commons Lang3 to 3.18.0
- Updated FlossWare Commons dependency to 1.10
- Improved JavaDoc throughout

### Fixed
- Port creation now validates URL parameters
- Proper exception handling when creating ports with invalid URLs

## [1.7] - Earlier

### Changed
- Dependency updates
- FlossWare Commons updated to support latest SOAP utilities

## Earlier Versions

Previous versions (1.0 - 1.6) included:
- Initial SOAP client generation from Salesforce WSDLs
- Factory pattern implementation
- Support for all major Salesforce APIs (Apex, Enterprise, Metadata, Partner, Tooling)
- Maven build integration with CXF code generation

---

## Upgrade Guide

### Upgrading from 1.7 to 1.8

#### Improvements You Get

- ✅ Comprehensive test coverage (25 tests)
- ✅ Better validation when creating ports
- ✅ Detailed README and documentation
- ✅ Latest Apache CXF 4.0.9
- ✅ Compatible with FlossWare Commons 1.9

#### Breaking Changes

**Port Creation with Invalid URLs Now Throws Exceptions**

Previously, creating a port with an invalid URL might fail silently or with unclear errors.

```java
// Now throws IllegalArgumentException for invalid URLs
PortFactoryEnum.METADATA.createPort(null);  // throws
PortFactoryEnum.METADATA.createPort("");    // throws
PortFactoryEnum.METADATA.createPort("  ");  // throws
```

**Impact**: If your code passes null/empty/blank URLs, you'll now get clear IllegalArgumentException instead of NPE or other cryptic errors.

#### Dependency Updates

The FlossWare Commons dependency was updated to 1.9, which includes:
- Improved SOAP utilities validation
- Better exception handling
- Enhanced security warnings

Ensure your project uses compatible versions:
```xml
<dependency>
    <groupId>org.flossware</groupId>
    <artifactId>commons</artifactId>
    <version>1.9</version>
</dependency>
```

No code changes required unless you were relying on specific error behavior with invalid URLs.
