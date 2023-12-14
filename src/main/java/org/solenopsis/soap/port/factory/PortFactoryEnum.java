package org.solenopsis.soap.port.factory;

import org.flossware.jcommons.util.SoapUtil;

/**
 * Contains all port type factories.
 *
 * @author sfloess
 */
public enum PortFactoryEnum {
    APEX(new ApexPortFactory()),
    ENTERPRISE(new EnterprisePortFactory()),
    METADATA(new MetadataPortFactory()),
    PARTNER(new PartnerPortFactory()),
    TOOLING(new ToolingPortFactory()),
    ;

    private final PortFactory factory;

    private PortFactoryEnum(final PortFactory factory) {
        this.factory = factory;
    }

    public <T> PortFactory<T> getPortFactory() {
        return factory;
    }

    public <T> T createPort() {
        return (T) getPortFactory().get();
    }

    public <T> T createPort(final String url) {
        return SoapUtil.setUrl(createPort(), url);
    }
}