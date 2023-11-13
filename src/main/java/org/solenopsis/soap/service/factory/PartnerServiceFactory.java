package org.solenopsis.soap.service.factory.jaxws;

import com.sforce.soap.partner.SforceService;
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
