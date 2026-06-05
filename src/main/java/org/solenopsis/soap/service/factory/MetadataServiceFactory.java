package org.solenopsis.soap.service.factory;

import org.solenopsis.soap.metadata.MetadataService;
import org.solenopsis.soap.service.ServiceWsdlEnum;

/**
 * Factory for creating Salesforce Metadata API service instances.
 * <p>
 * Creates {@link MetadataService} instances initialized with the Metadata WSDL URL.
 * The Metadata API allows retrieval, deployment, creation, update, and deletion of
 * customization information including custom objects, Apex classes, and other metadata.
 * </p>
 *
 * @author sfloess
 */
final class MetadataServiceFactory implements ServiceFactory<MetadataService> {
    /**
     * Cached service instance for reuse across all calls.
     * Volatile ensures visibility of the instance across threads.
     */
    private volatile MetadataService instance;

    /**
     * Returns the singleton MetadataService instance.
     * <p>
     * Uses double-checked locking to ensure thread-safe lazy initialization.
     * The service is initialized with the Metadata WSDL URL from the classpath
     * on first access and reused for all subsequent calls.
     * </p>
     *
     * @return the singleton MetadataService instance
     */
    @Override
    public MetadataService get() {
        MetadataService result = instance;
        if (result == null) {
            synchronized (this) {
                result = instance;
                if (result == null) {
                    instance = result = new MetadataService(ServiceWsdlEnum.METADATA.getUrl());
                }
            }
        }
        return result;
    }
}
