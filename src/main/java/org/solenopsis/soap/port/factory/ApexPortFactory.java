package org.solenopsis.soap.port.factory;

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
        return ((ApexService) ServiceFactoryEnum.APEX.createService()).getApex();
    }
}
