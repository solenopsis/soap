package org.solenopsis.soap.wsimport.port.factory;

import org.solenopsis.soap.port.factory.PortFactory;
import org.solenopsis.soap.wsimport.metadata.MetadataPortType;
import org.solenopsis.soap.wsimport.metadata.MetadataService;
import org.solenopsis.soap.wsimport.service.factory.ServiceEnum;

/**
 * Factory to create an Metadata port type.
 *
 * @author sfloess
 */
final class MetadataPortFactory implements PortFactory<MetadataPortType> {
    MetadataPortType createPort(final MetadataService service) {
        return service.getMetadata();
    }

    @Override
    public MetadataPortType get() {
        return createPort(ServiceEnum.METADATA.getService());
    }
}
