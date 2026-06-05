package org.solenopsis.soap.service.factory;

import org.solenopsis.soap.service.ServiceWsdlEnum;
import org.solenopsis.soap.tooling.SforceServiceService;

/**
 * Factory for creating Salesforce Tooling API service instances.
 * <p>
 * Creates {@link SforceServiceService} instances initialized with the Tooling WSDL URL.
 * The Tooling API provides access to developer tools, metadata operations, Apex class
 * management, debug log querying, and schema introspection capabilities.
 * </p>
 *
 * @author sfloess
 */
final class ToolingServiceFactory implements ServiceFactory<SforceServiceService> {
    /**
     * Cached service instance for reuse across all calls.
     * Volatile ensures visibility of the instance across threads.
     */
    private volatile SforceServiceService instance;

    /**
     * Returns the singleton Tooling SforceServiceService instance.
     * <p>
     * Uses double-checked locking to ensure thread-safe lazy initialization.
     * The service is initialized with the Tooling WSDL URL from the classpath
     * on first access and reused for all subsequent calls.
     * </p>
     *
     * @return the singleton SforceServiceService instance for the Tooling API
     */
    @Override
    public SforceServiceService get() {
        SforceServiceService result = instance;
        if (result == null) {
            synchronized (this) {
                result = instance;
                if (result == null) {
                    instance = result = new SforceServiceService(ServiceWsdlEnum.TOOLING.getUrl());
                }
            }
        }
        return result;
    }
}
