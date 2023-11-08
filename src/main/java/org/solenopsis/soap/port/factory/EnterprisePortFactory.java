package org.solenopsis.soap.port.factory;

import com.sforce.soap.enterprise.SforceService;
import com.sforce.soap.enterprise.Soap;
import org.solenopsis.soap.service.factory.jaxws.ServiceEnum;

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
