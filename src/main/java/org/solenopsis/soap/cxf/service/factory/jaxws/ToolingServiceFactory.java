package org.solenopsis.soap.cxf.service.factory.jaxws;

import org.solenopsis.soap.cxf.tooling.SforceServiceService;
import org.solenopsis.soap.service.ServiceWsdlEnum;
import org.solenopsis.soap.service.factory.ServiceFactory;

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
