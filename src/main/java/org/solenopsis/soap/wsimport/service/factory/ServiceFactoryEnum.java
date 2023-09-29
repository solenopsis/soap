package org.solenopsis.soap.wsimport.service.factory;

import javax.wsdl.Service;
import org.solenopsis.soap.service.factory.ServiceFactory;

/**
 * Factory to create actual Service implementations.
 *
 * @author sfloess
 */
public enum ServiceFactoryEnum {
    APEX(new ApexServiceFactory()),
    ENTERPRISE(new EnterpriseServiceFactory()),
    METADATA(new MetadataServiceFactory()),
    PARTNER(new PartnerServiceFactory()),
    TOOLING(new ToolingServiceFactory())
    ;

    private final ServiceFactory<?> serviceFactory;

    private ServiceFactoryEnum(final ServiceFactory<?> serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    public ServiceFactory<?> getServiceFactory() {
        return serviceFactory;
    }

    public <T extends Service> T createService() {
        return (T) getServiceFactory().get();
    }
}
