package org.solenopsis.soap.service.factory;

import org.solenopsis.soap.metadata.MetadataService;
import org.solenopsis.soap.service.ServiceWsdlEnum;

/**
 * Creates an instance of the client Metadata builtin web service.
 *
 * @author sfloess
 */
final class MetadataServiceFactory implements ServiceFactory<MetadataService> {
    @Override
    public MetadataService get() {
        return new MetadataService(ServiceWsdlEnum.METADATA.getUrl());
    }
}
