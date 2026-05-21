package org.solenopsis.soap.port.factory;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.solenopsis.soap.enterprise.SforceService;
import org.solenopsis.soap.enterprise.Soap;

/**
 * Factory for creating Salesforce Enterprise API port instances.
 * <p>
 * The Enterprise API provides a strongly-typed data API for interacting with
 * Salesforce data. It's optimized for use with a specific organization's schema
 * and is typically used when the schema is known at development time.
 * </p>
 * <p>
 * This factory uses Apache CXF's {@link JaxWsProxyFactoryBean} to create the port proxy.
 * </p>
 *
 * @author sfloess
 */
final class EnterprisePortFactory implements PortFactory<Soap> {
    /**
     * Creates an Enterprise port from the provided Enterprise service.
     * <p>
     * Note: This method creates a new proxy factory instead of using the service parameter.
     * </p>
     *
     * @param service the SforceService instance (currently unused)
     * @return a Soap port instance for making Enterprise API calls
     */
    Soap createPort(final SforceService service) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SforceService.class);
        return (Soap) factory.create();
    }

    /**
     * Creates a new Enterprise API port instance.
     * <p>
     * This method uses Apache CXF's proxy factory to create a SOAP client proxy
     * for the Enterprise API. The returned port can be used to make Enterprise API calls.
     * </p>
     *
     * @return a new Soap port instance
     */
    @Override
    public Soap get() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(Soap.class);
        return (Soap) factory.create();
    }
}
