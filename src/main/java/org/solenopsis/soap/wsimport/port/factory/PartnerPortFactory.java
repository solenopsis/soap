package org.solenopsis.soap.wsimport.port.factory;


import org.solenopsis.soap.port.factory.PortFactory;
import org.solenopsis.soap.wsimport.partner.SforceService;
import org.solenopsis.soap.wsimport.partner.Soap;
import org.solenopsis.soap.wsimport.service.factory.ServiceEnum;

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
        return createPort(ServiceEnum.PARTNER.getService());
    }
}
