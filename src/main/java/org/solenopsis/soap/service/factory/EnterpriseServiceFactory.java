package org.solenopsis.soap.service.factory;

import org.solenopsis.soap.enterprise.SforceService;
import org.solenopsis.soap.service.ServiceWsdlEnum;

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
