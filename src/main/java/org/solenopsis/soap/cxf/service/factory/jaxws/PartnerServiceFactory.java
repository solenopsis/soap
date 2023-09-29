package org.solenopsis.soap.cxf.service.factory.jaxws;

import org.solenopsis.soap.cxf.partner.SforceService;
import org.solenopsis.soap.service.ServiceWsdlEnum;
import org.solenopsis.soap.service.factory.ServiceFactory;

/**
 * Creates an instance of the client Partner builtin web service.
 *
 * @author sfloess
 */
final class PartnerServiceFactory implements ServiceFactory<SforceService> {
    @Override
    public SforceService get() {
        return new SforceService(ServiceWsdlEnum.PARTNER.getUrl());
    }
}
