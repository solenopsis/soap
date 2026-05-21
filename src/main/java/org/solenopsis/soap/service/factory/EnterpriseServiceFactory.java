package org.solenopsis.soap.service.factory;

import org.solenopsis.soap.enterprise.SforceService;
import org.solenopsis.soap.service.ServiceWsdlEnum;

/**
 * Factory for creating Salesforce Enterprise API service instances.
 * <p>
 * Creates {@link SforceService} instances initialized with the Enterprise WSDL URL.
 * The Enterprise API provides strongly-typed data operations optimized for use
 * with a specific organization's schema.
 * </p>
 *
 * @author sfloess
 */
final class EnterpriseServiceFactory implements ServiceFactory<SforceService> {
    /**
     * Creates a new Enterprise SforceService instance.
     * <p>
     * The service is initialized with the Enterprise WSDL URL from the classpath.
     * </p>
     *
     * @return a new SforceService instance for the Enterprise API
     */
    @Override
    public SforceService get() {
        return new SforceService(ServiceWsdlEnum.ENTERPRISE.getUrl());
    }
}
