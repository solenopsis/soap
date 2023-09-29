package org.solenopsis.soap.wsimport.service.factory;

import org.solenopsis.soap.service.ServiceWsdlEnum;
import org.solenopsis.soap.service.factory.ServiceFactory;
import org.solenopsis.soap.wsimport.tooling.SforceServiceService;


/**
 * Creates an instance of the client Tooling builtin web service.
 *
 * @author sfloess
 */
final class ToolingServiceFactory implements ServiceFactory<SforceServiceService> {
    @Override
    public SforceServiceService get() {
        return new SforceServiceService(ServiceWsdlEnum.TOOLING.getUrl());
    }
}
