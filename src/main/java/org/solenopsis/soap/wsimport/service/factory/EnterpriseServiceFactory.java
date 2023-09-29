package org.solenopsis.soap.wsimport.service.factory;

import org.solenopsis.soap.service.ServiceWsdlEnum;
import org.solenopsis.soap.service.factory.ServiceFactory;
import org.solenopsis.soap.wsimport.enterprise.SforceService;


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
