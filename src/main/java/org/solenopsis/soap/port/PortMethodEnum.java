package org.solenopsis.soap.port;

import jakarta.xml.ws.WebEndpoint;
import java.lang.reflect.Method;
import java.util.Objects;
import org.solenopsis.soap.service.factory.ServiceFactoryEnum;

/**
 * Convenience enum to hold the methods referring to port types of all built in
 * web services.
 *
 * @author sfloess
 */
public enum PortMethodEnum {
    APEX(ServiceFactoryEnum.APEX),
    ENTERPRISE(ServiceFactoryEnum.ENTERPRISE),
    METADATA(ServiceFactoryEnum.METADATA),
    PARTNER(ServiceFactoryEnum.PARTNER),
    TOOLING(ServiceFactoryEnum.TOOLING)
    ;

    private final Method portMethod;

    private PortMethodEnum(final Method[] methods) {
        Method toSet = null;

        for (final Method method : methods) {
            if (null != method.getAnnotation(WebEndpoint.class) && 0 == method.getParameterCount()) {
                toSet = method;

                break;
            }
        }

        this.portMethod = Objects.requireNonNull(toSet, "Port method not found!");
    }

    private PortMethodEnum(final ServiceFactoryEnum service) {
        this(service.createService().getClass().getMethods());
    }

    public Method getPortMethod() {
        return portMethod;
    }
}
