package org.solenopsis.soap.port.factory;

import org.solenopsis.soap.service.factory.ServiceFactoryEnum;
import org.solenopsis.soap.tooling.SforceServicePortType;
import org.solenopsis.soap.tooling.SforceServiceService;

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
        return createPort(ServiceFactoryEnum.TOOLING.createService());
    }
}
