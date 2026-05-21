package org.solenopsis.soap.service;

import org.junit.jupiter.api.Test;
import org.solenopsis.soap.service.factory.ServiceFactoryEnum;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for {@link ServiceEnum}.
 * <p>
 * Validates that all service enum constants correctly aggregate QName, WSDL URL,
 * and factory information for each Salesforce API.
 * </p>
 *
 * @author sfloess
 */
class ServiceEnumTest {

    /**
     * Verifies that all expected enum constants are present.
     * Should have exactly 5 values for the 5 Salesforce APIs.
     */
    @Test
    void testAllEnumValues() {
        assertEquals(5, ServiceEnum.values().length);
    }

    /**
     * Validates that the APEX enum constant correctly aggregates QName, WSDL, and factory.
     */
    @Test
    void testApex() {
        ServiceEnum apex = ServiceEnum.APEX;
        assertNotNull(apex);
        assertNotNull(apex.getQName());
        assertNotNull(apex.getWsdl());
        assertNotNull(apex.getFactory());
        assertEquals(ServiceQNameEnum.APEX, apex.getQName());
        assertEquals(ServiceWsdlEnum.APEX, apex.getWsdl());
        assertEquals(ServiceFactoryEnum.APEX, apex.getFactory());
    }

    /**
     * Validates that the ENTERPRISE enum constant correctly aggregates QName, WSDL, and factory.
     */
    @Test
    void testEnterprise() {
        ServiceEnum enterprise = ServiceEnum.ENTERPRISE;
        assertNotNull(enterprise);
        assertNotNull(enterprise.getQName());
        assertNotNull(enterprise.getWsdl());
        assertNotNull(enterprise.getFactory());
        assertEquals(ServiceQNameEnum.ENTERPRISE, enterprise.getQName());
        assertEquals(ServiceWsdlEnum.ENTERPRISE, enterprise.getWsdl());
        assertEquals(ServiceFactoryEnum.ENTERPRISE, enterprise.getFactory());
    }

    /**
     * Validates that the METADATA enum constant correctly aggregates QName, WSDL, and factory.
     */
    @Test
    void testMetadata() {
        ServiceEnum metadata = ServiceEnum.METADATA;
        assertNotNull(metadata);
        assertNotNull(metadata.getQName());
        assertNotNull(metadata.getWsdl());
        assertNotNull(metadata.getFactory());
        assertEquals(ServiceQNameEnum.METADATA, metadata.getQName());
        assertEquals(ServiceWsdlEnum.METADATA, metadata.getWsdl());
        assertEquals(ServiceFactoryEnum.METADATA, metadata.getFactory());
    }

    /**
     * Validates that the PARTNER enum constant correctly aggregates QName, WSDL, and factory.
     */
    @Test
    void testPartner() {
        ServiceEnum partner = ServiceEnum.PARTNER;
        assertNotNull(partner);
        assertNotNull(partner.getQName());
        assertNotNull(partner.getWsdl());
        assertNotNull(partner.getFactory());
        assertEquals(ServiceQNameEnum.PARTNER, partner.getQName());
        assertEquals(ServiceWsdlEnum.PARTNER, partner.getWsdl());
        assertEquals(ServiceFactoryEnum.PARTNER, partner.getFactory());
    }

    /**
     * Validates that the TOOLING enum constant correctly aggregates QName, WSDL, and factory.
     */
    @Test
    void testTooling() {
        ServiceEnum tooling = ServiceEnum.TOOLING;
        assertNotNull(tooling);
        assertNotNull(tooling.getQName());
        assertNotNull(tooling.getWsdl());
        assertNotNull(tooling.getFactory());
        assertEquals(ServiceQNameEnum.TOOLING, tooling.getQName());
        assertEquals(ServiceWsdlEnum.TOOLING, tooling.getWsdl());
        assertEquals(ServiceFactoryEnum.TOOLING, tooling.getFactory());
    }

    /**
     * Validates that the {@link Enum#valueOf(Class, String)} method works correctly
     * for all enum constants.
     */
    @Test
    void testValueOf() {
        assertEquals(ServiceEnum.APEX, ServiceEnum.valueOf("APEX"));
        assertEquals(ServiceEnum.ENTERPRISE, ServiceEnum.valueOf("ENTERPRISE"));
        assertEquals(ServiceEnum.METADATA, ServiceEnum.valueOf("METADATA"));
        assertEquals(ServiceEnum.PARTNER, ServiceEnum.valueOf("PARTNER"));
        assertEquals(ServiceEnum.TOOLING, ServiceEnum.valueOf("TOOLING"));
    }
}
