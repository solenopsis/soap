package org.solenopsis.soap.cxf.service.factory.jaxws;

import org.solenopsis.soap.cxf.enterprise.SforceService;
import org.solenopsis.soap.service.ServiceWsdlEnum;
import org.solenopsis.soap.service.factory.ServiceFactory;

/**
 * Creates an instance of the client Enterprise builtin web service.
 *
 * @author sfloess
 */
final class EnterpriseServiceFactory implements ServiceFactory<SforceService> {
    @Override
    public SforceService get() {
        return new SforceService(ServiceWsdlEnum.ENTERPRISE.getUrl());
    }
}
