package org.solenopsis.soap.cxf.service.factory.jaxws;

import org.solenopsis.soap.cxf.metadata.MetadataService;
import org.solenopsis.soap.service.ServiceWsdlEnum;
import org.solenopsis.soap.service.factory.ServiceFactory;

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
