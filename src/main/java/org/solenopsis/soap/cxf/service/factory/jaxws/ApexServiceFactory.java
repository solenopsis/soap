package org.solenopsis.soap.cxf.service.factory.jaxws;

import org.solenopsis.soap.cxf.apex.ApexService;
import org.solenopsis.soap.service.ServiceWsdlEnum;
import org.solenopsis.soap.service.factory.ServiceFactory;

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
