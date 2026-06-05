package org.solenopsis.soap.port.factory;

import org.solenopsis.soap.enterprise.Soap;
import org.solenopsis.soap.enterprise.SforceService;
import org.solenopsis.soap.service.factory.ServiceFactoryEnum;

/**
 * Factory for creating Salesforce Enterprise API port instances.
 * <p>
 * The Enterprise API provides a strongly-typed data API for interacting with
 * Salesforce data. It's optimized for use with a specific organization's schema
 * and is typically used when the schema is known at development time.
 * </p>
 *
 * @author sfloess
 */
final class EnterprisePortFactory implements PortFactory<Soap> {
    /**
     * Creates an Enterprise port from the provided Enterprise service.
     *
     * @param service the SforceService instance to extract the port from
     * @return a Soap instance for making Enterprise API calls
     */
    Soap createPort(final SforceService service) {
        return service.getSoap();
    }

    /**
     * Creates a new Enterprise API port instance.
     * <p>
     * This method creates the underlying Enterprise service and extracts its port.
     * The returned port can be used to make Enterprise API SOAP calls.
     * </p>
     *
     * @return a new Soap instance
     */
    @Override
    public Soap get() {
        return createPort((SforceService) ServiceFactoryEnum.ENTERPRISE.createService());
    }
}
