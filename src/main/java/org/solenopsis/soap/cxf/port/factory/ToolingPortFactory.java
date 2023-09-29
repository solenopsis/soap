package org.solenopsis.soap.cxf.port.factory;

import org.solenopsis.soap.cxf.tooling.SforceServicePortType;
import org.solenopsis.soap.cxf.tooling.SforceServiceService;
import org.solenopsis.soap.cxf.service.factory.jaxws.ServiceEnum;
import org.solenopsis.soap.port.factory.PortFactory;

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
