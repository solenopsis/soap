package org.solenopsis.soap.cxf.port.factory;

import org.solenopsis.soap.cxf.enterprise.SforceService;
import org.solenopsis.soap.cxf.enterprise.Soap;
import org.solenopsis.soap.cxf.service.factory.jaxws.ServiceEnum;
import org.solenopsis.soap.port.factory.PortFactory;

/**
 * Factory to create an Enterprise port type.
 *
 * @author sfloess
 */
final class EnterprisePortFactory implements PortFactory<Soap> {
    Soap createPort(final SforceService service) {
        return service.getSoap();
    }

    @Override
    public Soap get() {
        return createPort(ServiceEnum.ENTERPRISE.getService());
    }
}
