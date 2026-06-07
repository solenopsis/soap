package org.solenopsis.soap.port.factory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link PortFactoryEnum}.
 * <p>
 * Validates port creation functionality including default port creation,
 * URL-based port creation, and proper validation of input parameters.
 * </p>
 *
 * @author sfloess
 */
class PortFactoryEnumTest {

    /**
     * Verifies that all expected enum constants are present.
     * Should have exactly 5 values for the 5 Salesforce APIs.
     */
    @Test
    void testAllEnumValues() {
        assertEquals(5, PortFactoryEnum.values().length);
    }

    /**
     * Validates that the APEX factory is not null and can provide a port factory instance.
     */
    @Test
    void testApexFactory() {
        assertNotNull(PortFactoryEnum.APEX);
        assertNotNull(PortFactoryEnum.APEX.getPortFactory());
    }

    /**
     * Validates that the ENTERPRISE factory is not null and can provide a port factory instance.
     */
    @Test
    void testEnterpriseFactory() {
        assertNotNull(PortFactoryEnum.ENTERPRISE);
        assertNotNull(PortFactoryEnum.ENTERPRISE.getPortFactory());
    }

    /**
     * Validates that the METADATA factory is not null and can provide a port factory instance.
     */
    @Test
    void testMetadataFactory() {
        assertNotNull(PortFactoryEnum.METADATA);
        assertNotNull(PortFactoryEnum.METADATA.getPortFactory());
    }

    /**
     * Validates that the PARTNER factory is not null and can provide a port factory instance.
     */
    @Test
    void testPartnerFactory() {
        assertNotNull(PortFactoryEnum.PARTNER);
        assertNotNull(PortFactoryEnum.PARTNER.getPortFactory());
    }

    /**
     * Validates that the TOOLING factory is not null and can provide a port factory instance.
     */
    @Test
    void testToolingFactory() {
        assertNotNull(PortFactoryEnum.TOOLING);
        assertNotNull(PortFactoryEnum.TOOLING.getPortFactory());
    }

    /**
     * Validates that port creation without a URL succeeds and returns a non-null port instance.
     */
    @Test
    void testCreatePort() {
        assertNotNull(PortFactoryEnum.METADATA.createPort());
    }

    /**
     * Validates that port creation with a valid URL succeeds and returns a non-null port instance.
     */
    @Test
    void testCreatePortWithUrl() {
        Object port = PortFactoryEnum.METADATA.createPort("https://test.salesforce.com/services/Soap/m/58.0");
        assertNotNull(port);
    }

    /**
     * Validates that port creation with a null URL throws {@link IllegalArgumentException}.
     */
    @Test
    void testCreatePortWithNullUrl() {
        assertThrows(IllegalArgumentException.class, () ->
            PortFactoryEnum.METADATA.createPort(null));
    }

    /**
     * Validates that port creation with an empty URL throws {@link IllegalArgumentException}.
     */
    @Test
    void testCreatePortWithEmptyUrl() {
        assertThrows(IllegalArgumentException.class, () ->
            PortFactoryEnum.METADATA.createPort(""));
    }

    /**
     * Validates that port creation with a blank URL (whitespace only) throws {@link IllegalArgumentException}.
     */
    @Test
    void testCreatePortWithBlankUrl() {
        assertThrows(IllegalArgumentException.class, () ->
            PortFactoryEnum.METADATA.createPort("   "));
    }

    /**
     * Validates that port creation with a malformed URL throws {@link IllegalArgumentException}.
     */
    @Test
    void testCreatePortWithMalformedUrl() {
        assertThrows(IllegalArgumentException.class, () ->
            PortFactoryEnum.METADATA.createPort("not-a-valid-url"));
    }
}
