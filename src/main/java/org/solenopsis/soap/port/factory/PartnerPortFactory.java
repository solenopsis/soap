package org.solenopsis.soap.port.factory;

import com.sforce.soap.partner.SforceService;
import com.sforce.soap.partner.Soap;
import org.solenopsis.soap.service.factory.ServiceFactoryEnum;

/**
 * Factory to create an Partner port type.
 *
 * @author sfloess
 */
final class PartnerPortFactory implements PortFactory<Soap> {
    Soap createPort(final SforceService service) {
        return service.getSoap();
    }

    @Override
    public Soap get() {
        return createPort(ServiceFactoryEnum.PARTNER.createService());
    }
}
