package org.solenopsis.soap.service;

/**
 *
 * @author sfloess
 */
public enum ServiceEnum {
    APEX(ServiceWsdlEnum.APEX, ServiceQNameEnum.APEX),
    ENTERPRISE(ServiceWsdlEnum.ENTERPRISE, ServiceQNameEnum.ENTERPRISE),
    METADATA(ServiceWsdlEnum.METADATA, ServiceQNameEnum.METADATA),
    PARTNER(ServiceWsdlEnum.PARTNER, ServiceQNameEnum.PARTNER),
    TOOLING(ServiceWsdlEnum.TOOLING, ServiceQNameEnum.TOOLING),
    ;

    private ServiceEnum(final ServiceWsdlEnum wsdl, final ServiceQNameEnum qname) {

    }
}
