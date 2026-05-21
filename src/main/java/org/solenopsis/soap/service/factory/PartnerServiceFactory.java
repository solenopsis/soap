package org.solenopsis.soap.service.factory;

import org.solenopsis.soap.partner.SforceService;
import org.solenopsis.soap.service.ServiceWsdlEnum;

/**
 * Factory for creating Salesforce Partner API service instances.
 * <p>
 * Creates {@link SforceService} instances initialized with the Partner WSDL URL.
 * The Partner API provides loosely-typed, dynamic data operations that work with
 * any Salesforce organization without knowing the schema at development time.
 * </p>
 *
 * @author sfloess
 */
final class PartnerServiceFactory implements ServiceFactory<SforceService> {
    /**
     * Creates a new Partner SforceService instance.
     * <p>
     * The service is initialized with the Partner WSDL URL from the classpath.
     * </p>
     *
     * @return a new SforceService instance for the Partner API
     */
    @Override
    public SforceService get() {
        return new SforceService(ServiceWsdlEnum.PARTNER.getUrl());
    }
}
