package org.solenopsis.soap.port;

import jakarta.xml.ws.WebEndpoint;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PortMethodEnumTest {

    @Test
    void testAllEnumValues() {
        assertEquals(5, PortMethodEnum.values().length);
    }

    @Test
    void testApexPortMethod() {
        assertNotNull(PortMethodEnum.APEX);
        Method method = PortMethodEnum.APEX.getPortMethod();
        assertNotNull(method);
        assertNotNull(method.getAnnotation(WebEndpoint.class));
        assertEquals(0, method.getParameterCount());
    }

    @Test
    void testEnterprisePortMethod() {
        assertNotNull(PortMethodEnum.ENTERPRISE);
        Method method = PortMethodEnum.ENTERPRISE.getPortMethod();
        assertNotNull(method);
        assertNotNull(method.getAnnotation(WebEndpoint.class));
        assertEquals(0, method.getParameterCount());
    }

    @Test
    void testMetadataPortMethod() {
        assertNotNull(PortMethodEnum.METADATA);
        Method method = PortMethodEnum.METADATA.getPortMethod();
        assertNotNull(method);
        assertNotNull(method.getAnnotation(WebEndpoint.class));
        assertEquals(0, method.getParameterCount());
    }

    @Test
    void testPartnerPortMethod() {
        assertNotNull(PortMethodEnum.PARTNER);
        Method method = PortMethodEnum.PARTNER.getPortMethod();
        assertNotNull(method);
        assertNotNull(method.getAnnotation(WebEndpoint.class));
        assertEquals(0, method.getParameterCount());
    }

    @Test
    void testToolingPortMethod() {
        assertNotNull(PortMethodEnum.TOOLING);
        Method method = PortMethodEnum.TOOLING.getPortMethod();
        assertNotNull(method);
        assertNotNull(method.getAnnotation(WebEndpoint.class));
        assertEquals(0, method.getParameterCount());
    }

    @Test
    void testValueOf() {
        assertEquals(PortMethodEnum.APEX, PortMethodEnum.valueOf("APEX"));
        assertEquals(PortMethodEnum.ENTERPRISE, PortMethodEnum.valueOf("ENTERPRISE"));
        assertEquals(PortMethodEnum.METADATA, PortMethodEnum.valueOf("METADATA"));
        assertEquals(PortMethodEnum.PARTNER, PortMethodEnum.valueOf("PARTNER"));
        assertEquals(PortMethodEnum.TOOLING, PortMethodEnum.valueOf("TOOLING"));
    }

    @Test
    void testAllPortMethodsHaveWebEndpointAnnotation() {
        for (PortMethodEnum portMethod : PortMethodEnum.values()) {
            Method method = portMethod.getPortMethod();
            assertTrue(method.isAnnotationPresent(WebEndpoint.class),
                    portMethod.name() + " port method should have @WebEndpoint annotation");
        }
    }
}
