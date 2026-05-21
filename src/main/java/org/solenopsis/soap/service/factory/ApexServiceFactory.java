package org.solenopsis.soap.service.factory;

import org.solenopsis.soap.apex.ApexService;
import org.solenopsis.soap.service.ServiceWsdlEnum;

/**
 * Factory for creating Salesforce Apex API service instances.
 * <p>
 * Creates {@link ApexService} instances initialized with the Apex WSDL URL.
 * The Apex API allows execution of anonymous Apex code and management of debug logs.
 * </p>
 *
 * @author sfloess
 */
final class ApexServiceFactory implements ServiceFactory<ApexService> {
    /**
     * Creates a new ApexService instance.
     * <p>
     * The service is initialized with the Apex WSDL URL from the classpath.
     * </p>
     *
     * @return a new ApexService instance
     */
    @Override
    public ApexService get() {
        return new ApexService(ServiceWsdlEnum.APEX.getUrl());
    }
}
