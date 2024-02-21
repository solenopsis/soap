package org.solenopsis.soap.service;

import org.solenopsis.soap.service.factory.ServiceFactoryEnum;

/**
 * Joins together import pieces of service info.
 *
 * @author sfloess
 */
public enum ServiceEnum {
    APEX(ServiceQNameEnum.APEX, ServiceWsdlEnum.APEX, ServiceFactoryEnum.APEX),
    ENTERPRISE(ServiceQNameEnum.ENTERPRISE, ServiceWsdlEnum.ENTERPRISE, ServiceFactoryEnum.ENTERPRISE),
    METADATA(ServiceQNameEnum.METADATA, ServiceWsdlEnum.METADATA,  ServiceFactoryEnum.METADATA),
    PARTNER(ServiceQNameEnum.PARTNER, ServiceWsdlEnum.PARTNER, ServiceFactoryEnum.PARTNER),
    TOOLING(ServiceQNameEnum.TOOLING, ServiceWsdlEnum.TOOLING, ServiceFactoryEnum.TOOLING),
    ;

    private final ServiceQNameEnum qname;
    private final ServiceWsdlEnum wsdl;
    private final ServiceFactoryEnum factory;

    private ServiceEnum(final ServiceQNameEnum qname, final ServiceWsdlEnum wsdl, ServiceFactoryEnum factory) {
        this.qname = qname;
        this.wsdl = wsdl;
        this.factory = factory;
    }

    public ServiceQNameEnum getQName() {
        return qname;
    }

    public ServiceWsdlEnum getWsdl() {
        return wsdl;
    }

    public ServiceFactoryEnum getFactory() {
        return factory;
    };
}
