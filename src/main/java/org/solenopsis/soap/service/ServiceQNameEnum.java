package org.solenopsis.soap.service;

import jakarta.xml.ws.WebServiceClient;
import javax.xml.namespace.QName;
import org.solenopsis.soap.service.factory.ServiceFactoryEnum;

/**
 * Convenience email holding all builtin web service QNames.
 *
 * @author sfloess
 */
public enum ServiceQNameEnum {
    APEX(ServiceFactoryEnum.APEX),
    ENTERPRISE(ServiceFactoryEnum.ENTERPRISE),
    METADATA(ServiceFactoryEnum.METADATA),
    PARTNER(ServiceFactoryEnum.PARTNER),
    TOOLING(ServiceFactoryEnum.TOOLING),
    ;

    private final QName qname;

    private ServiceQNameEnum(final WebServiceClient webServiceClient) {
        qname = new QName(webServiceClient.targetNamespace(), webServiceClient.name());
    }

    private ServiceQNameEnum(final ServiceFactoryEnum service) {
        this(service.createService().getClass().getAnnotation(WebServiceClient.class));
    }

    public QName getQName() {
        return qname;
    }
}
