package org.solenopsis.soap.port;

import jakarta.xml.ws.WebEndpoint;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link PortMethodEnum}.
 * <p>
 * Validates that all port method enum constants correctly identify and store
 * the {@link WebEndpoint} annotated methods for creating SOAP port instances.
 * </p>
 *
 * @author sfloess
 */
class PortMethodEnumTest {

    /**
     * Verifies that all expected enum constants are present.
     * Should have exactly 5 values for the 5 Salesforce APIs.
     */
    @Test
    void testAllEnumValues() {
        assertEquals(5, PortMethodEnum.values().length);
    }

    /**
     * Validates that the APEX enum correctly identifies the WebEndpoint annotated method
     * with zero parameters.
     */
    @Test
    void testApexPortMethod() {
        assertNotNull(PortMethodEnum.APEX);
        Method method = PortMethodEnum.APEX.getPortMethod();
        assertNotNull(method);
        assertNotNull(method.getAnnotation(WebEndpoint.class));
        assertEquals(0, method.getParameterCount());
    }

    /**
     * Validates that the ENTERPRISE enum correctly identifies the WebEndpoint annotated method
     * with zero parameters.
     */
    @Test
    void testEnterprisePortMethod() {
        assertNotNull(PortMethodEnum.ENTERPRISE);
        Method method = PortMethodEnum.ENTERPRISE.getPortMethod();
        assertNotNull(method);
        assertNotNull(method.getAnnotation(WebEndpoint.class));
        assertEquals(0, method.getParameterCount());
    }

    /**
     * Validates that the METADATA enum correctly identifies the WebEndpoint annotated method
     * with zero parameters.
     */
    @Test
    void testMetadataPortMethod() {
        assertNotNull(PortMethodEnum.METADATA);
        Method method = PortMethodEnum.METADATA.getPortMethod();
        assertNotNull(method);
        assertNotNull(method.getAnnotation(WebEndpoint.class));
        assertEquals(0, method.getParameterCount());
    }

    /**
     * Validates that the PARTNER enum correctly identifies the WebEndpoint annotated method
     * with zero parameters.
     */
    @Test
    void testPartnerPortMethod() {
        assertNotNull(PortMethodEnum.PARTNER);
        Method method = PortMethodEnum.PARTNER.getPortMethod();
        assertNotNull(method);
        assertNotNull(method.getAnnotation(WebEndpoint.class));
        assertEquals(0, method.getParameterCount());
    }

    /**
     * Validates that the TOOLING enum correctly identifies the WebEndpoint annotated method
     * with zero parameters.
     */
    @Test
    void testToolingPortMethod() {
        assertNotNull(PortMethodEnum.TOOLING);
        Method method = PortMethodEnum.TOOLING.getPortMethod();
        assertNotNull(method);
        assertNotNull(method.getAnnotation(WebEndpoint.class));
        assertEquals(0, method.getParameterCount());
    }

    /**
     * Validates that the {@link Enum#valueOf(Class, String)} method works correctly
     * for all enum constants.
     */
    @Test
    void testValueOf() {
        assertEquals(PortMethodEnum.APEX, PortMethodEnum.valueOf("APEX"));
        assertEquals(PortMethodEnum.ENTERPRISE, PortMethodEnum.valueOf("ENTERPRISE"));
        assertEquals(PortMethodEnum.METADATA, PortMethodEnum.valueOf("METADATA"));
        assertEquals(PortMethodEnum.PARTNER, PortMethodEnum.valueOf("PARTNER"));
        assertEquals(PortMethodEnum.TOOLING, PortMethodEnum.valueOf("TOOLING"));
    }

    /**
     * Validates that all port methods across all enum constants have the
     * {@link WebEndpoint} annotation, which marks them as SOAP port creation methods.
     */
    @Test
    void testAllPortMethodsHaveWebEndpointAnnotation() {
        for (PortMethodEnum portMethod : PortMethodEnum.values()) {
            Method method = portMethod.getPortMethod();
            assertTrue(method.isAnnotationPresent(WebEndpoint.class),
                    portMethod.name() + " port method should have @WebEndpoint annotation");
        }
    }
}
