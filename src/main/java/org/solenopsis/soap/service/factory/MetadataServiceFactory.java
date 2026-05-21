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
     * Creates a new MetadataService instance.
     * <p>
     * The service is initialized with the Metadata WSDL URL from the classpath.
     * </p>
     *
     * @return a new MetadataService instance
     */
    @Override
    public MetadataService get() {
        return new MetadataService(ServiceWsdlEnum.METADATA.getUrl());
    }
}
