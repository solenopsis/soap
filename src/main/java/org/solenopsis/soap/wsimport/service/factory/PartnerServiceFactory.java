package org.solenopsis.soap.wsimport.service.factory;

import org.solenopsis.soap.service.ServiceWsdlEnum;
import org.solenopsis.soap.service.factory.ServiceFactory;
import org.solenopsis.soap.wsimport.partner.SforceService;


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
