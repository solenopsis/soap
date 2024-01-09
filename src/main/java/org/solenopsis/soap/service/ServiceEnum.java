package org.solenopsis.soap.service;

import jakarta.xml.ws.Service;
import org.solenopsis.soap.service.factory.ServiceFactoryEnum;

/**
 *
 * @author sfloess
 */
public enum ServiceEnum {
    APEX(ServiceQNameEnum.APEX, ServiceWsdlEnum.APEX, ServiceFactoryEnum.APEX),
    ENTERPRISE(ServiceQNameEnum.ENTERPRISE, ServiceWsdlEnum.ENTERPRISE, ServiceFactoryEnum.ENTERPRISE),
    METADATA(ServiceQNameEnum.METADATA, ServiceWsdlEnum.METADATA, ServiceFactoryEnum.METADATA),
    PARTNER(ServiceQNameEnum.PARTNER, ServiceWsdlEnum.PARTNER, ServiceFactoryEnum.PARTNER),
    TOOLING(ServiceQNameEnum.TOOLING, ServiceWsdlEnum.TOOLING, ServiceFactoryEnum.TOOLING),
    ;

    private final Service service;

    private ServiceEnum(final ServiceQNameEnum qname, final ServiceWsdlEnum wsdl, ServiceFactoryEnum factory) {
        this.service = factory.createService();
    }

    <T extends Service> T getService() {
        return (T) service;
    }
}
