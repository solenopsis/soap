package org.solenopsis.soap.wsimport.service.factory;

import org.solenopsis.soap.service.ServiceWsdlEnum;
import org.solenopsis.soap.service.factory.ServiceFactory;
import org.solenopsis.soap.wsimport.metadata.MetadataService;


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
