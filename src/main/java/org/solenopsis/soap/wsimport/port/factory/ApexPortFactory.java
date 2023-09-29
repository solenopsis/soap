package org.solenopsis.soap.wsimport.port.factory;

import org.solenopsis.soap.port.factory.PortFactory;
import org.solenopsis.soap.wsimport.apex.ApexPortType;
import org.solenopsis.soap.wsimport.apex.ApexService;
import org.solenopsis.soap.wsimport.service.factory.ServiceEnum;



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
