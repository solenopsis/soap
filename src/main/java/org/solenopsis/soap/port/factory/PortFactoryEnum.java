package org.solenopsis.soap.port.factory;

import org.flossware.jcommons.util.SoapUtil;

/**
 * Enum containing all port type factories for Salesforce SOAP APIs.
 * <p>
 * This enum provides a convenient way to create SOAP port instances for each
 * Salesforce API. Ports can be created with default endpoints or with custom
 * URLs for different Salesforce environments (production, sandbox, custom domains).
 * </p>
 * <p>
 * Example usage:
 * <pre>
 * // Create port with default endpoint
 * MetadataPortType port = PortFactoryEnum.METADATA.createPort();
 *
 * // Create port with custom endpoint
 * MetadataPortType port = PortFactoryEnum.METADATA.createPort(
 *     "https://na1.salesforce.com/services/Soap/m/58.0"
 * );
 * </pre>
 * </p>
 *
 * @author sfloess
 */
public enum PortFactoryEnum {
    /** Factory for creating Apex API ports. */
    APEX(new ApexPortFactory()),

    /** Factory for creating Enterprise API ports. */
    ENTERPRISE(new EnterprisePortFactory()),

    /** Factory for creating Metadata API ports. */
    METADATA(new MetadataPortFactory()),

    /** Factory for creating Partner API ports. */
    PARTNER(new PartnerPortFactory()),

    /** Factory for creating Tooling API ports. */
    TOOLING(new ToolingPortFactory()),
    ;

    /** The underlying port factory implementation. */
    private final PortFactory factory;

    /**
     * Constructs a PortFactoryEnum with the specified factory implementation.
     *
     * @param factory the PortFactory implementation for this API
     */
    private PortFactoryEnum(final PortFactory factory) {
        this.factory = factory;
    }

    /**
     * Returns the underlying port factory.
     *
     * @param <T> the port type
     * @return the PortFactory for creating port instances
     */
    public <T> PortFactory<T> getPortFactory() {
        return factory;
    }

    /**
     * Creates a SOAP port instance with the default endpoint.
     * <p>
     * The default endpoint is determined by the WSDL file included with the library.
     * After creation, use {@link SoapUtil#setUrl(Object, String)} or
     * {@link #createPort(String)} to point to a specific Salesforce instance.
     * </p>
     *
     * @param <T> the port type
     * @return a new SOAP port instance
     */
    public <T> T createPort() {
        return (T) getPortFactory().get();
    }

    /**
     * Creates a SOAP port instance configured with the specified endpoint URL.
     * <p>
     * This method creates a port and immediately sets its endpoint to the provided URL.
     * The URL should point to a valid Salesforce SOAP API endpoint, such as:
     * </p>
     * <ul>
     *   <li>Production: {@code https://login.salesforce.com/services/Soap/...}</li>
     *   <li>Sandbox: {@code https://test.salesforce.com/services/Soap/...}</li>
     *   <li>Custom: {@code https://mydomain.my.salesforce.com/services/Soap/...}</li>
     * </ul>
     *
     * @param <T> the port type
     * @param url the SOAP endpoint URL to use
     * @return a new SOAP port instance configured with the specified URL
     * @throws IllegalArgumentException if the URL is null, empty, or blank
     */
    public <T> T createPort(final String url) {
        return SoapUtil.setUrl(createPort(), url);
    }
}