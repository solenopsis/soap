package org.solenopsis.soap.port.factory;

import com.sforce.soap.tooling.SforceServicePortType;
import com.sforce.soap.tooling.SforceServiceService;
import org.solenopsis.soap.service.factory.jaxws.ServiceEnum;

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
