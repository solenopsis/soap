package org.solenopsis.soap.port;

import jakarta.xml.ws.WebEndpoint;
import java.lang.reflect.Method;
import java.util.Objects;
import org.solenopsis.soap.apex.ApexService;
import org.solenopsis.soap.enterprise.SforceService;
import org.solenopsis.soap.metadata.MetadataService;
import org.solenopsis.soap.tooling.SforceServiceService;

/**
 * Convenience enum to hold the {@link WebEndpoint} annotated methods for creating
 * port types from Salesforce web services.
 * <p>
 * This enum automatically discovers and stores the port creation method for each
 * service type by finding the parameterless method annotated with {@code @WebEndpoint}.
 * These methods are used to obtain SOAP port instances from their respective service classes.
 * </p>
 *
 * @author sfloess
 */
public enum PortMethodEnum {
    /** Apex API port creation method. */
    APEX(ApexService.class),

    /** Enterprise API port creation method. */
    ENTERPRISE(SforceService.class),

    /** Metadata API port creation method. */
    METADATA(MetadataService.class),

    /** Partner API port creation method. */
    PARTNER(org.solenopsis.soap.partner.SforceService.class),

    /** Tooling API port creation method. */
    TOOLING(SforceServiceService.class)
    ;

    /** The Method object representing the port creation method. */
    private final Method portMethod;

    /**
     * Constructs a PortMethodEnum by finding the {@link WebEndpoint} annotated method
     * from the provided method array.
     *
     * @param methods array of methods to search for the port creation method
     * @throws NullPointerException if no suitable method is found
     */
    PortMethodEnum(final Method[] methods) {
        Method toSet = null;

        for (final Method method : methods) {
            if (null != method.getAnnotation(WebEndpoint.class) && 0 == method.getParameterCount()) {
                toSet = method;

                break;
            }
        }

        this.portMethod = Objects.requireNonNull(toSet, "Port method not found!");
    }

    /**
     * Constructs a PortMethodEnum by extracting the port creation method from the
     * specified service class.
     *
     * @param serviceClass the service class to extract the port method from
     */
    PortMethodEnum(final Class<?> serviceClass) {
        this(serviceClass.getMethods());
    }

    /**
     * Returns the {@link Method} object for creating a port instance.
     * <p>
     * This method is annotated with {@code @WebEndpoint} and takes no parameters.
     * It can be invoked reflectively to obtain a SOAP port instance from a service.
     * </p>
     *
     * @return the Method for creating a port instance
     */
    public Method getPortMethod() {
        return portMethod;
    }
}
