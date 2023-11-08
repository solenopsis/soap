package org.solenopsis.soap.service.factory.jaxws;

import jakarta.xml.ws.Service;

/**
 * Generic enum of Service instances.
 *
 * @author sfloess
 */
public enum ServiceEnum {
    APEX(ServiceFactoryEnum.APEX),
    ENTERPRISE(ServiceFactoryEnum.ENTERPRISE),
    METADATA(ServiceFactoryEnum.METADATA),
    PARTNER(ServiceFactoryEnum.PARTNER),
    TOOLING(ServiceFactoryEnum.TOOLING);

    final Service service;

    private ServiceEnum(final ServiceFactoryEnum factory) {
        this.service = factory.createService();
    }

    public <T extends Service> T getService() {
        return (T) service;
    }
}
