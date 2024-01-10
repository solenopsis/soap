package org.solenopsis.soap.service;

import org.solenopsis.soap.SubUrlEnum;
import org.solenopsis.soap.service.factory.ServiceFactoryEnum;

/**
 * Joins together import pieces of service info.
 * 
 * @author sfloess
 */
public enum ServiceEnum {
    APEX(ServiceQNameEnum.APEX, ServiceWsdlEnum.APEX, SubUrlEnum.APEX, ServiceFactoryEnum.APEX),
    ENTERPRISE(ServiceQNameEnum.ENTERPRISE, ServiceWsdlEnum.ENTERPRISE, SubUrlEnum.ENTERPRISE, ServiceFactoryEnum.ENTERPRISE),
    METADATA(ServiceQNameEnum.METADATA, ServiceWsdlEnum.METADATA, SubUrlEnum.METADATA, ServiceFactoryEnum.METADATA),
    PARTNER(ServiceQNameEnum.PARTNER, ServiceWsdlEnum.PARTNER, SubUrlEnum.PARTNER, ServiceFactoryEnum.PARTNER),
    TOOLING(ServiceQNameEnum.TOOLING, ServiceWsdlEnum.TOOLING, SubUrlEnum.TOOLING, ServiceFactoryEnum.TOOLING),
    ;

    private final ServiceQNameEnum qname;
    private final ServiceWsdlEnum wsdl;
    private final SubUrlEnum subUrl;
    private final ServiceFactoryEnum factory;

    private ServiceEnum(final ServiceQNameEnum qname, final ServiceWsdlEnum wsdl, final SubUrlEnum subUrl, ServiceFactoryEnum factory) {
        this.qname = qname;
        this.wsdl = wsdl;
        this.subUrl = subUrl;
        this.factory = factory;
    }

    public ServiceQNameEnum getQName() {
        return qname;
    }

    public ServiceWsdlEnum getWsdl() {
        return wsdl;
    }

    public SubUrlEnum getSubUrl() {
        return subUrl;
    }

    public ServiceFactoryEnum getFactory() {
        return factory;
    };
}
