package org.solenopsis.soap.service.factory;

import jakarta.xml.ws.Service;

/**
 * Enum containing all service factories for Salesforce SOAP APIs.
 * <p>
 * This enum provides a centralized way to create JAX-WS {@link Service} instances
 * for each Salesforce API. Services are the top-level SOAP objects from which
 * port instances are obtained.
 * </p>
 * <p>
 * Example usage:
 * <pre>
 * MetadataService service = ServiceFactoryEnum.METADATA.createService();
 * MetadataPortType port = service.getMetadata();
 * </pre>
 * </p>
 *
 * @author sfloess
 */
public enum ServiceFactoryEnum {
    /** Factory for creating Apex API services. */
    APEX(new ApexServiceFactory()),

    /** Factory for creating Enterprise API services. */
    ENTERPRISE(new EnterpriseServiceFactory()),

    /** Factory for creating Metadata API services. */
    METADATA(new MetadataServiceFactory()),

    /** Factory for creating Partner API services. */
    PARTNER(new PartnerServiceFactory()),

    /** Factory for creating Tooling API services. */
    TOOLING(new ToolingServiceFactory())
    ;

    /** The underlying service factory implementation. */
    private final ServiceFactory<?> serviceFactory;

    /**
     * Constructs a ServiceFactoryEnum with the specified factory implementation.
     *
     * @param serviceFactory the ServiceFactory implementation for this API
     */
    ServiceFactoryEnum(final ServiceFactory<?> serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    /**
     * Returns the underlying service factory.
     *
     * @return the ServiceFactory for creating service instances
     */
    public ServiceFactory<?> getServiceFactory() {
        return serviceFactory;
    }

    /**
     * Creates a JAX-WS Service instance for this API.
     * <p>
     * <strong>Note:</strong> This method returns a cached singleton instance.
     * The service is lazily initialized on first access using thread-safe
     * double-checked locking and reused for all subsequent calls. This improves
     * performance by avoiding repeated WSDL parsing (6.6MB total across all services).
     * </p>
     * <p>
     * The service is initialized with the appropriate WSDL URL and can be used
     * to obtain port instances for making SOAP calls.
     * </p>
     *
     * @return a cached Service instance of the type appropriate for this enum constant
     */
    public Service createService() {
        return getServiceFactory().get();
    }
}
