package org.solenopsis.soap.wsimport.port.factory;

import org.solenopsis.soap.port.factory.PortFactory;
import org.solenopsis.soap.wsimport.service.factory.ServiceEnum;
import org.solenopsis.soap.wsimport.tooling.SforceServicePortType;
import org.solenopsis.soap.wsimport.tooling.SforceServiceService;



/**
 * Factory to create an Tooling port type.
 *
 * @author sfloess
 */
final class ToolingPortFactory implements PortFactory<SforceServicePortType> {
    SforceServicePortType createPort(final SforceServiceService service) {
        return service.getSforceService();
    }

    @Override
    public SforceServicePortType get() {
        return createPort(ServiceEnum.TOOLING.getService());
    }
}
