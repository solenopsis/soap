package org.solenopsis.soap.port.factory;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.solenopsis.soap.enterprise.SforceService;
import org.solenopsis.soap.enterprise.Soap;

/**
 * Factory to create an Enterprise port type.
 *
 * @author sfloess
 */
final class EnterprisePortFactory implements PortFactory<Soap> {
    Soap createPort(final SforceService service) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SforceService.class);
        return (Soap) factory.create();
    }

    @Override
    public Soap get() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(Soap.class);
        return (Soap) factory.create();
//        return createPort(ServiceEnum.ENTERPRISE.getService());
    }
}
