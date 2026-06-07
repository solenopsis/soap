package org.solenopsis.soap.port.factory;

import org.solenopsis.soap.partner.SforceService;
import org.solenopsis.soap.partner.Soap;
import org.solenopsis.soap.service.factory.ServiceFactoryEnum;

/**
 * Factory for creating Salesforce Partner API port instances.
 * <p>
 * The Partner API provides a loosely-typed, dynamic data API for interacting with
 * Salesforce data. It's designed to work with any Salesforce organization without
 * knowing the schema at development time, making it ideal for building generic
 * tools and integrations.
 * </p>
 *
 * @author sfloess
 */
final class PartnerPortFactory implements PortFactory<Soap> {
    /**
     * Creates a new Partner API port instance.
     * <p>
     * This method creates the underlying Partner service and extracts its port.
     * The returned port can be used to make Partner API SOAP calls.
     * </p>
     *
     * @return a new Soap port instance
     */
    @Override
    public Soap get() {
        return ((SforceService) ServiceFactoryEnum.PARTNER.createService()).getSoap();
    }
}
