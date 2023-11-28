package org.solenopsis.soap.port.factory;

import com.sforce.soap.enterprise.SforceService;
import com.sforce.soap.enterprise.Soap;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

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
