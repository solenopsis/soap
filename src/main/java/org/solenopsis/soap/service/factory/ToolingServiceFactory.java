package org.solenopsis.soap.service.factory;

import org.solenopsis.soap.service.ServiceWsdlEnum;
import org.solenopsis.soap.tooling.SforceServiceService;

/**
 * Factory for creating Salesforce Tooling API service instances.
 * <p>
 * Creates {@link SforceServiceService} instances initialized with the Tooling WSDL URL.
 * The Tooling API provides access to developer tools, metadata operations, Apex class
 * management, debug log querying, and schema introspection capabilities.
 * </p>
 *
 * @author sfloess
 */
final class ToolingServiceFactory implements ServiceFactory<SforceServiceService> {
    /**
     * Creates a new Tooling SforceServiceService instance.
     * <p>
     * The service is initialized with the Tooling WSDL URL from the classpath.
     * </p>
     *
     * @return a new SforceServiceService instance for the Tooling API
     */
    @Override
    public SforceServiceService get() {
        return new SforceServiceService(ServiceWsdlEnum.TOOLING.getUrl());
    }
}
