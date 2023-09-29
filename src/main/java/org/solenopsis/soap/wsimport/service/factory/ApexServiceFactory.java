package org.solenopsis.soap.wsimport.service.factory;


import org.solenopsis.soap.service.ServiceWsdlEnum;
import org.solenopsis.soap.service.factory.ServiceFactory;
import org.solenopsis.soap.wsimport.apex.ApexService;

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
