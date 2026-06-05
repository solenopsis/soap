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
     * Cached service instance for reuse across all calls.
     * Volatile ensures visibility of the instance across threads.
     */
    private volatile ApexService instance;

    /**
     * Returns the singleton ApexService instance.
     * <p>
     * Uses double-checked locking to ensure thread-safe lazy initialization.
     * The service is initialized with the Apex WSDL URL from the classpath
     * on first access and reused for all subsequent calls.
     * </p>
     *
     * @return the singleton ApexService instance
     */
    @Override
    public ApexService get() {
        ApexService result = instance;
        if (result == null) {
            synchronized (this) {
                result = instance;
                if (result == null) {
                    instance = result = new ApexService(ServiceWsdlEnum.APEX.getUrl());
                }
            }
        }
        return result;
    }
}
