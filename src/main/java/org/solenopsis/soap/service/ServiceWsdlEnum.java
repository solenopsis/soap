package org.solenopsis.soap.service;

import java.net.URL;

/**
 * Enum holding URLs to Salesforce WSDL (Web Services Description Language) files.
 * <p>
 * WSDL files define the structure and operations available in SOAP web services.
 * This enum loads the WSDL files from the classpath as resources, making them
 * available for SOAP client generation and runtime service creation.
 * </p>
 * <p>
 * The WSDL files are located in {@code src/main/resources/wsdl/} and are packaged
 * with the library JAR.
 * </p>
 *
 * @author sfloess
 */
public enum ServiceWsdlEnum {
    /** Apex API WSDL for executing anonymous Apex code. */
    APEX("wsdl/soap-apex.wsdl"),

    /** Enterprise API WSDL for strongly-typed data operations. */
    ENTERPRISE("wsdl/soap-enterprise.wsdl"),

    /** Metadata API WSDL for deploying and retrieving metadata. */
    METADATA("wsdl/soap-metadata.wsdl"),

    /** Partner API WSDL for dynamically-typed data operations. */
    PARTNER("wsdl/soap-partner.wsdl"),

    /** Tooling API WSDL for developer tools and schema introspection. */
    TOOLING("wsdl/soap-tooling.wsdl"),
    ;

    /** The URL pointing to the WSDL file on the classpath. */
    final URL wsdl;

    /**
     * Constructs a ServiceWsdlEnum by loading the WSDL file from the classpath.
     *
     * @param resource the classpath resource path to the WSDL file
     * @throws IllegalStateException if the WSDL resource is not found on the classpath
     */
    ServiceWsdlEnum(final String resource) {
        this.wsdl = ServiceWsdlEnum.class.getClassLoader().getResource(resource);
        if (this.wsdl == null) {
            throw new IllegalStateException("WSDL resource not found on classpath: " + resource);
        }
    }

    /**
     * Returns the {@link URL} pointing to the WSDL file.
     * <p>
     * This URL can be used to create SOAP services at runtime or to access
     * the WSDL definition programmatically.
     * </p>
     *
     * @return the URL to the WSDL file (never null)
     */
    public URL getUrl() {
        return wsdl;
    }
}
