package org.solenopsis.soap.port.factory;

import org.solenopsis.soap.metadata.MetadataPortType;
import org.solenopsis.soap.metadata.MetadataService;
import org.solenopsis.soap.service.factory.ServiceFactoryEnum;

/**
 * Factory for creating Salesforce Metadata API port instances.
 * <p>
 * The Metadata API allows you to retrieve, deploy, create, update, and delete
 * customization information (metadata) for your organization. This includes
 * custom objects, Apex classes, Visualforce pages, and other configuration.
 * </p>
 *
 * @author sfloess
 */
final class MetadataPortFactory implements PortFactory<MetadataPortType> {
    /**
     * Creates a Metadata port from the provided Metadata service.
     *
     * @param service the MetadataService instance to extract the port from
     * @return a MetadataPortType instance for making Metadata API calls
     */
    MetadataPortType createPort(final MetadataService service) {
        return service.getMetadata();
    }

    /**
     * Creates a new Metadata API port instance.
     * <p>
     * This method creates the underlying Metadata service and extracts its port.
     * The returned port can be used to make Metadata API SOAP calls.
     * </p>
     *
     * @return a new MetadataPortType instance
     */
    @Override
    public MetadataPortType get() {
        return createPort((MetadataService) ServiceFactoryEnum.METADATA.createService());
    }
}
