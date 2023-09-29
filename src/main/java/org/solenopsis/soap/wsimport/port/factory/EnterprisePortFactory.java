package org.solenopsis.soap.wsimport.port.factory;


import org.solenopsis.soap.port.factory.PortFactory;
import org.solenopsis.soap.wsimport.enterprise.SforceService;
import org.solenopsis.soap.wsimport.enterprise.Soap;
import org.solenopsis.soap.wsimport.service.factory.ServiceEnum;

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
