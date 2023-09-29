package org.solenopsis.soap.cxf.service;

import jakarta.xml.ws.WebServiceClient;
import javax.xml.namespace.QName;
import org.solenopsis.soap.cxf.service.factory.jaxws.ServiceEnum;

/**
 * Convenience email holding all builtin web service QNames.
 *
 * @author sfloess
 */
public enum ServiceQNameEnum {
    APEX(ServiceEnum.APEX),
    ENTERPRISE(ServiceEnum.ENTERPRISE),
    METADATA(ServiceEnum.METADATA),
    PARTNER(ServiceEnum.PARTNER),
    TOOLING(ServiceEnum.TOOLING)
    ;

    private final QName qname;

    private ServiceQNameEnum(final WebServiceClient webServiceClient) {
        qname = new QName(webServiceClient.targetNamespace(), webServiceClient.name());
    }

    private ServiceQNameEnum(final ServiceEnum service) {
        this(service.getService().getClass().getAnnotation(WebServiceClient.class));
    }

    public QName getQName() {
        return qname;
    }
}
