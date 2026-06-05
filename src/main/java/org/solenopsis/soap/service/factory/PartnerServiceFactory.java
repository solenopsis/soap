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
     * Cached service instance for reuse across all calls.
     * Volatile ensures visibility of the instance across threads.
     */
    private volatile SforceService instance;

    /**
     * Returns the singleton Partner SforceService instance.
     * <p>
     * Uses double-checked locking to ensure thread-safe lazy initialization.
     * The service is initialized with the Partner WSDL URL from the classpath
     * on first access and reused for all subsequent calls.
     * </p>
     *
     * @return the singleton SforceService instance for the Partner API
     */
    @Override
    public SforceService get() {
        SforceService result = instance;
        if (result == null) {
            synchronized (this) {
                result = instance;
                if (result == null) {
                    instance = result = new SforceService(ServiceWsdlEnum.PARTNER.getUrl());
                }
            }
        }
        return result;
    }
}
