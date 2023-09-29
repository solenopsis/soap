package org.solenopsis.soap.cxf.port.factory;

import org.solenopsis.soap.cxf.metadata.MetadataPortType;
import org.solenopsis.soap.cxf.metadata.MetadataService;
import org.solenopsis.soap.cxf.service.factory.jaxws.ServiceEnum;
import org.solenopsis.soap.port.factory.PortFactory;

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
