package org.solenopsis.soap.service;

import java.net.URL;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link ServiceWsdlEnum}.
 * <p>
 * Validates that all WSDL enum constants correctly load and provide access to
 * the WSDL files from the classpath for each Salesforce API.
 * </p>
 *
 * @author sfloess
 */
class ServiceWsdlEnumTest {

    /**
     * Verifies that all expected enum constants are present.
     * Should have exactly 5 values for the 5 Salesforce APIs.
     */
    @Test
    void testAllEnumValues() {
        assertEquals(5, ServiceWsdlEnum.values().length);
    }

    /**
     * Validates that the APEX WSDL URL is not null and points to the correct WSDL file.
     */
    @Test
    void testApexWsdl() {
        assertNotNull(ServiceWsdlEnum.APEX);
        URL url = ServiceWsdlEnum.APEX.getUrl();
        assertNotNull(url);
        assertTrue(url.toString().contains("soap-apex.wsdl"));
    }

    /**
     * Validates that the ENTERPRISE WSDL URL is not null and points to the correct WSDL file.
     */
    @Test
    void testEnterpriseWsdl() {
        assertNotNull(ServiceWsdlEnum.ENTERPRISE);
        URL url = ServiceWsdlEnum.ENTERPRISE.getUrl();
        assertNotNull(url);
        assertTrue(url.toString().contains("soap-enterprise.wsdl"));
    }

    /**
     * Validates that the METADATA WSDL URL is not null and points to the correct WSDL file.
     */
    @Test
    void testMetadataWsdl() {
        assertNotNull(ServiceWsdlEnum.METADATA);
        URL url = ServiceWsdlEnum.METADATA.getUrl();
        assertNotNull(url);
        assertTrue(url.toString().contains("soap-metadata.wsdl"));
    }

    /**
     * Validates that the PARTNER WSDL URL is not null and points to the correct WSDL file.
     */
    @Test
    void testPartnerWsdl() {
        assertNotNull(ServiceWsdlEnum.PARTNER);
        URL url = ServiceWsdlEnum.PARTNER.getUrl();
        assertNotNull(url);
        assertTrue(url.toString().contains("soap-partner.wsdl"));
    }

    /**
     * Validates that the TOOLING WSDL URL is not null and points to the correct WSDL file.
     */
    @Test
    void testToolingWsdl() {
        assertNotNull(ServiceWsdlEnum.TOOLING);
        URL url = ServiceWsdlEnum.TOOLING.getUrl();
        assertNotNull(url);
        assertTrue(url.toString().contains("soap-tooling.wsdl"));
    }

    /**
     * Validates that all WSDL URLs are unique across the different APIs.
     * Each API should have its own distinct WSDL file.
     */
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

    /**
     * Validates that the {@link Enum#valueOf(Class, String)} method works correctly
     * for all enum constants.
     */
    @Test
    void testValueOf() {
        assertEquals(ServiceWsdlEnum.APEX, ServiceWsdlEnum.valueOf("APEX"));
        assertEquals(ServiceWsdlEnum.ENTERPRISE, ServiceWsdlEnum.valueOf("ENTERPRISE"));
        assertEquals(ServiceWsdlEnum.METADATA, ServiceWsdlEnum.valueOf("METADATA"));
        assertEquals(ServiceWsdlEnum.PARTNER, ServiceWsdlEnum.valueOf("PARTNER"));
        assertEquals(ServiceWsdlEnum.TOOLING, ServiceWsdlEnum.valueOf("TOOLING"));
    }
}
