package org.solenopsis.soap.service;

import org.solenopsis.soap.service.factory.ServiceFactoryEnum;

/**
 * Aggregates all important pieces of service information for Salesforce APIs.
 * <p>
 * This enum combines the QName, WSDL URL, and factory for each Salesforce service,
 * providing a unified way to access all service-related metadata and creation
 * capabilities in one place.
 * </p>
 * <p>
 * Each enum constant represents one of the five major Salesforce APIs:
 * Apex, Enterprise, Metadata, Partner, and Tooling.
 * </p>
 *
 * @author sfloess
 */
public enum ServiceEnum {
    /** Apex API service aggregation. */
    APEX(ServiceQNameEnum.APEX, ServiceWsdlEnum.APEX, ServiceFactoryEnum.APEX),

    /** Enterprise API service aggregation. */
    ENTERPRISE(ServiceQNameEnum.ENTERPRISE, ServiceWsdlEnum.ENTERPRISE, ServiceFactoryEnum.ENTERPRISE),

    /** Metadata API service aggregation. */
    METADATA(ServiceQNameEnum.METADATA, ServiceWsdlEnum.METADATA,  ServiceFactoryEnum.METADATA),

    /** Partner API service aggregation. */
    PARTNER(ServiceQNameEnum.PARTNER, ServiceWsdlEnum.PARTNER, ServiceFactoryEnum.PARTNER),

    /** Tooling API service aggregation. */
    TOOLING(ServiceQNameEnum.TOOLING, ServiceWsdlEnum.TOOLING, ServiceFactoryEnum.TOOLING),
    ;

    /** The QName for this service. */
    private final ServiceQNameEnum qname;

    /** The WSDL URL for this service. */
    private final ServiceWsdlEnum wsdl;

    /** The factory for creating service instances. */
    private final ServiceFactoryEnum factory;

    /**
     * Constructs a ServiceEnum with all associated service metadata.
     *
     * @param qname the QName enum for this service
     * @param wsdl the WSDL URL enum for this service
     * @param factory the factory enum for creating service instances
     */
    private ServiceEnum(final ServiceQNameEnum qname, final ServiceWsdlEnum wsdl, ServiceFactoryEnum factory) {
        this.qname = qname;
        this.wsdl = wsdl;
        this.factory = factory;
    }

    /**
     * Returns the QName enum for this service.
     *
     * @return the ServiceQNameEnum containing the service's qualified name
     */
    public ServiceQNameEnum getQName() {
        return qname;
    }

    /**
     * Returns the WSDL URL enum for this service.
     *
     * @return the ServiceWsdlEnum containing the WSDL file URL
     */
    public ServiceWsdlEnum getWsdl() {
        return wsdl;
    }

    /**
     * Returns the factory enum for creating service instances.
     *
     * @return the ServiceFactoryEnum for creating this service
     */
    public ServiceFactoryEnum getFactory() {
        return factory;
    }
}
