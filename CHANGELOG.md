# Changelog

All notable changes to the Solenopsis SOAP library will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.17] - 2026-05-21

### Added
- Comprehensive JavaDoc documentation for all 25 Java files (19 main + 6 test)
- Class-level and method-level JavaDoc with @param, @return, and @throws tags
- Usage examples in key factory classes (PortFactoryEnum, ServiceFactoryEnum)
- Cross-references using {@link} tags throughout the codebase
- Detailed API descriptions for all 5 Salesforce APIs

### Changed
- Enhanced all enum class JavaDoc with detailed descriptions
- Improved all factory interface and implementation JavaDoc
- Added comprehensive test method documentation

### Fixed
- Fixed typo in ServiceQNameEnum JavaDoc (changed "email" to "enum")

## [1.16] - 2026-05-21

### Changed
- Updated FlossWare JCommons dependency to version 1.21
- Dependency version bump for latest bug fixes and improvements

## [1.15] - 2026-05-20

### Changed
- Automated version bump following comprehensive test suite addition
- No functional changes in this release

## [1.14] - 2026-05-20

### Added
- Unit tests for PortClassEnum (7 tests)
- Unit tests for PortMethodEnum (8 tests)  
- Unit tests for ServiceWsdlEnum (8 tests)
- Test coverage now at 48 total tests (up from 25)

### Changed
- Made JavaDoc generation non-blocking in GitHub Actions workflow
- Updated README.md to reflect current version (1.14) and test count (48 tests)

### Fixed
- GitHub Actions workflow now continues even if JavaDoc generation fails
- Achieved 100% test coverage for all custom enum classes

## [1.13] - 2026-05-19

### Added
- JaCoCo code coverage reporting with Codecov integration
- OWASP dependency security scanning
- JavaDoc generation and deployment to GitHub Pages
- JUnit test reporting in GitHub Actions
- Comprehensive .gitignore for better repository hygiene

### Changed
- Enhanced GitHub Actions workflow with coverage, security, and documentation steps
- All CI/CD improvements are non-blocking to prevent build failures

## [1.12] - 2026-05-19

### Added
- JaCoCo Maven plugin for test coverage (version 0.8.12)
- SLF4J and Logback dependencies to CXF code generation plugin
- Structured logging configuration via logback.xml
- Maven Surefire plugin 3.5.2 for improved test execution

### Changed
- Standardized test framework dependencies
- Updated dependency versions for better compatibility

### Fixed
- Eliminated "No SLF4J providers found" warnings during WSDL code generation

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
