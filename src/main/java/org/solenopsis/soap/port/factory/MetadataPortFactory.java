package org.solenopsis.soap.port.factory;

import org.solenopsis.soap.metadata.MetadataPortType;
import org.solenopsis.soap.metadata.MetadataService;
import org.solenopsis.soap.service.factory.ServiceFactoryEnum;

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
        return createPort(ServiceFactoryEnum.METADATA.createService());
    }
}
