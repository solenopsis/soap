package org.solenopsis.soap.port.factory;

import org.solenopsis.soap.service.factory.ServiceFactoryEnum;
import org.solenopsis.soap.tooling.SforceServicePortType;
import org.solenopsis.soap.tooling.SforceServiceService;

/**
 * Factory for creating Salesforce Tooling API port instances.
 * <p>
 * The Tooling API provides access to developer tools and metadata operations.
 * It enables you to build custom development tools, retrieve and manage Apex classes
 * and triggers, query debug logs, and perform other development-related operations.
 * </p>
 *
 * @author sfloess
 */
final class ToolingPortFactory implements PortFactory<SforceServicePortType> {
    /**
     * Creates a Tooling port from the provided Tooling service.
     *
     * @param service the SforceServiceService instance to extract the port from
     * @return a SforceServicePortType instance for making Tooling API calls
     */
    SforceServicePortType createPort(final SforceServiceService service) {
        return service.getSforceService();
    }

    /**
     * Creates a new Tooling API port instance.
     * <p>
     * This method creates the underlying Tooling service and extracts its port.
     * The returned port can be used to make Tooling API SOAP calls.
     * </p>
     *
     * @return a new SforceServicePortType instance
     */
    @Override
    public SforceServicePortType get() {
        return createPort(ServiceFactoryEnum.TOOLING.createService());
    }
}
