package org.solenopsis.soap.cxf.port.factory;

import org.solenopsis.soap.cxf.apex.ApexPortType;
import org.solenopsis.soap.cxf.apex.ApexService;
import org.solenopsis.soap.cxf.service.factory.jaxws.ServiceEnum;
import org.solenopsis.soap.port.factory.PortFactory;

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
