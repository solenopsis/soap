package org.solenopsis.soap.service;

import java.net.URL;

/**
 * Hold URLs to our WSDLs.
 *
 * @author sfloess
 */
public enum ServiceWsdlEnum {
    APEX("wsdl/soap-apex.wsdl"),
    ENTERPRISE("wsdl/soap-enterprise.wsdl"),
    METADATA("wsdl/soap-metadata.wsdl"),
    PARTNER("wsdl/soap-partner.wsdl"),
    TOOLING("wsdl/soap-tooling.wsdl"),
    ;

    final URL wsdl;

    ServiceWsdlEnum(final String resource) {
        this.wsdl = ServiceWsdlEnum.class.getClassLoader().getResource(resource);
    }

    public URL getUrl() {
        return wsdl;
    }
}
