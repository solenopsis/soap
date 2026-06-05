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
     * Cached service instance for reuse across all calls.
     * Volatile ensures visibility of the instance across threads.
     */
    private volatile SforceService instance;

    /**
     * Returns the singleton Enterprise SforceService instance.
     * <p>
     * Uses double-checked locking to ensure thread-safe lazy initialization.
     * The service is initialized with the Enterprise WSDL URL from the classpath
     * on first access and reused for all subsequent calls.
     * </p>
     *
     * @return the singleton SforceService instance for the Enterprise API
     */
    @Override
    public SforceService get() {
        SforceService result = instance;
        if (result == null) {
            synchronized (this) {
                result = instance;
                if (result == null) {
                    instance = result = new SforceService(ServiceWsdlEnum.ENTERPRISE.getUrl());
                }
            }
        }
        return result;
    }
}
