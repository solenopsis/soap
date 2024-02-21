package org.solenopsis.soap.service.factory;

import org.solenopsis.soap.apex.ApexService;
import org.solenopsis.soap.service.ServiceWsdlEnum;

/**
 *
 * @author sfloess
 */
final class ApexServiceFactory implements ServiceFactory<ApexService> {
    @Override
    public ApexService get() {
        return new ApexService(ServiceWsdlEnum.APEX.getUrl());
    }
}
