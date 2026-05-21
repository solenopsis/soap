package org.solenopsis.soap.port;

import org.solenopsis.soap.apex.ApexPortType;
import org.solenopsis.soap.enterprise.Soap;
import org.solenopsis.soap.metadata.MetadataPortType;
import org.solenopsis.soap.tooling.SforceServicePortType;


/**
 * Convenience enum that holds all port type classes for built-in Salesforce web services.
 * <p>
 * This enum provides a centralized mapping between service types and their corresponding
 * SOAP port interface classes. Each enum constant represents one of the five major
 * Salesforce APIs (Apex, Enterprise, Metadata, Partner, Tooling).
 * </p>
 *
 * @author sfloess
 */
public enum PortClassEnum {
    /** Apex API port type for executing anonymous Apex code and managing logs. */
    APEX(ApexPortType.class),

    /** Enterprise API port type for full-featured data operations with strong typing. */
    ENTERPRISE(Soap.class),

    /** Partner API port type for flexible data operations with dynamic typing. */
    PARTNER(org.solenopsis.soap.partner.Soap.class),

    /** Metadata API port type for deploying and retrieving metadata. */
    METADATA(MetadataPortType.class),

    /** Tooling API port type for developer tools and schema introspection. */
    TOOLING(SforceServicePortType.class);

    /** The Java class representing this port type interface. */
    final Class portType;

    /**
     * Constructs a PortClassEnum with the specified port type class.
     *
     * @param portType the Java class representing the SOAP port interface
     */
    PortClassEnum(final Class portType) {
        this.portType = portType;
    }

    /**
     * Returns the Java class for this port type.
     *
     * @return the Class object representing the SOAP port interface
     */
    public Class getPortType() {
        return portType;
    }
}
