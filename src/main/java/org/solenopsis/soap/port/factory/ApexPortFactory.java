package org.solenopsis.soap.port.factory;

import org.solenopsis.soap.apex.ApexPortType;
import org.solenopsis.soap.apex.ApexService;
import org.solenopsis.soap.service.factory.jaxws.ServiceEnum;

/**
 * Factory to create an Apex port type.
 *
 * @author sfloess
 */
final class ApexPortFactory implements PortFactory<ApexPortType> {
    ApexPortType createPort(final ApexService service) {
        return service.getApex();
    }

    @Override
    public ApexPortType get() {
        return createPort(ServiceEnum.APEX.getService());
    }
}
