package org.solenopsis.soap.service;

import java.net.URL;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ServiceWsdlEnumTest {

    @Test
    void testAllEnumValues() {
        assertEquals(5, ServiceWsdlEnum.values().length);
    }

    @Test
    void testApexWsdl() {
        assertNotNull(ServiceWsdlEnum.APEX);
        URL url = ServiceWsdlEnum.APEX.getUrl();
        assertNotNull(url);
        assertTrue(url.toString().contains("soap-apex.wsdl"));
    }

    @Test
    void testEnterpriseWsdl() {
        assertNotNull(ServiceWsdlEnum.ENTERPRISE);
        URL url = ServiceWsdlEnum.ENTERPRISE.getUrl();
        assertNotNull(url);
        assertTrue(url.toString().contains("soap-enterprise.wsdl"));
    }

    @Test
    void testMetadataWsdl() {
        assertNotNull(ServiceWsdlEnum.METADATA);
        URL url = ServiceWsdlEnum.METADATA.getUrl();
        assertNotNull(url);
        assertTrue(url.toString().contains("soap-metadata.wsdl"));
    }

    @Test
    void testPartnerWsdl() {
        assertNotNull(ServiceWsdlEnum.PARTNER);
        URL url = ServiceWsdlEnum.PARTNER.getUrl();
        assertNotNull(url);
        assertTrue(url.toString().contains("soap-partner.wsdl"));
    }

    @Test
    void testToolingWsdl() {
        assertNotNull(ServiceWsdlEnum.TOOLING);
        URL url = ServiceWsdlEnum.TOOLING.getUrl();
        assertNotNull(url);
        assertTrue(url.toString().contains("soap-tooling.wsdl"));
    }

    @Test
    void testWsdlUrlsAreUnique() {
        URL apex = ServiceWsdlEnum.APEX.getUrl();
        URL enterprise = ServiceWsdlEnum.ENTERPRISE.getUrl();
        URL metadata = ServiceWsdlEnum.METADATA.getUrl();
        URL partner = ServiceWsdlEnum.PARTNER.getUrl();
        URL tooling = ServiceWsdlEnum.TOOLING.getUrl();

        assertNotEquals(apex, enterprise);
        assertNotEquals(apex, metadata);
        assertNotEquals(apex, partner);
        assertNotEquals(apex, tooling);
        assertNotEquals(enterprise, metadata);
        assertNotEquals(enterprise, partner);
        assertNotEquals(enterprise, tooling);
        assertNotEquals(metadata, partner);
        assertNotEquals(metadata, tooling);
        assertNotEquals(partner, tooling);
    }

    @Test
    void testValueOf() {
        assertEquals(ServiceWsdlEnum.APEX, ServiceWsdlEnum.valueOf("APEX"));
        assertEquals(ServiceWsdlEnum.ENTERPRISE, ServiceWsdlEnum.valueOf("ENTERPRISE"));
        assertEquals(ServiceWsdlEnum.METADATA, ServiceWsdlEnum.valueOf("METADATA"));
        assertEquals(ServiceWsdlEnum.PARTNER, ServiceWsdlEnum.valueOf("PARTNER"));
        assertEquals(ServiceWsdlEnum.TOOLING, ServiceWsdlEnum.valueOf("TOOLING"));
    }
}
