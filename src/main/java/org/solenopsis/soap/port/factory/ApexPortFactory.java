package org.solenopsis.soap.port.factory;

import java.util.Objects;
import org.solenopsis.soap.apex.ApexPortType;
import org.solenopsis.soap.apex.ApexService;
import org.solenopsis.soap.service.factory.ServiceFactoryEnum;

/**
 * Factory for creating Salesforce Apex API port instances.
 * <p>
 * The Apex API allows you to execute anonymous Apex code, manage debug logs,
 * and perform other Apex-related operations.
 * </p>
 *
 * @author sfloess
 */
final class ApexPortFactory implements PortFactory<ApexPortType> {
    /**
     * Creates an Apex port from the provided Apex service.
     *
     * @param service the ApexService instance to extract the port from
     * @return an ApexPortType instance for making Apex API calls
     * @throws NullPointerException if service is null
     */
    ApexPortType createPort(final ApexService service) {
        Objects.requireNonNull(service, "service cannot be null");
        return service.getApex();
    }

    /**
     * Creates a new Apex API port instance.
     * <p>
     * This method creates the underlying Apex service and extracts its port.
     * The returned port can be used to make Apex API SOAP calls.
     * </p>
     *
     * @return a new ApexPortType instance
     */
    @Override
    public ApexPortType get() {
        return createPort(ServiceFactoryEnum.APEX.createService());
    }
}
