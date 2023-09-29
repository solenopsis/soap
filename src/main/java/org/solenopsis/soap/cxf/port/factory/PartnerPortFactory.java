package org.solenopsis.soap.cxf.port.factory;

import org.solenopsis.soap.cxf.partner.SforceService;
import org.solenopsis.soap.cxf.partner.Soap;
import org.solenopsis.soap.cxf.service.factory.jaxws.ServiceEnum;
import org.solenopsis.soap.port.factory.PortFactory;

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
