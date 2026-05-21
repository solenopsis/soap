package org.solenopsis.soap.port;

import org.junit.jupiter.api.Test;
import org.solenopsis.soap.apex.ApexPortType;
import org.solenopsis.soap.enterprise.Soap;
import org.solenopsis.soap.metadata.MetadataPortType;
import org.solenopsis.soap.tooling.SforceServicePortType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for {@link PortClassEnum}.
 * <p>
 * Validates that all port class enum constants are properly configured with
 * their corresponding port type classes for each Salesforce API.
 * </p>
 *
 * @author sfloess
 */
class PortClassEnumTest {

    /**
     * Verifies that all expected enum constants are present.
     * Should have exactly 5 values for the 5 Salesforce APIs.
     */
    @Test
    void testAllEnumValues() {
        assertEquals(5, PortClassEnum.values().length);
    }

    /**
     * Validates that the APEX enum constant returns the correct port type class.
     */
    @Test
    void testApexPortType() {
        assertNotNull(PortClassEnum.APEX);
        assertEquals(ApexPortType.class, PortClassEnum.APEX.getPortType());
    }

    /**
     * Validates that the ENTERPRISE enum constant returns the correct port type class.
     */
    @Test
    void testEnterprisePortType() {
        assertNotNull(PortClassEnum.ENTERPRISE);
        assertEquals(Soap.class, PortClassEnum.ENTERPRISE.getPortType());
    }

    /**
     * Validates that the METADATA enum constant returns the correct port type class.
     */
    @Test
    void testMetadataPortType() {
        assertNotNull(PortClassEnum.METADATA);
        assertEquals(MetadataPortType.class, PortClassEnum.METADATA.getPortType());
    }

    /**
     * Validates that the PARTNER enum constant returns the correct port type class.
     */
    @Test
    void testPartnerPortType() {
        assertNotNull(PortClassEnum.PARTNER);
        assertEquals(org.solenopsis.soap.partner.Soap.class, PortClassEnum.PARTNER.getPortType());
    }

    /**
     * Validates that the TOOLING enum constant returns the correct port type class.
     */
    @Test
    void testToolingPortType() {
        assertNotNull(PortClassEnum.TOOLING);
        assertEquals(SforceServicePortType.class, PortClassEnum.TOOLING.getPortType());
    }

    /**
     * Validates that the {@link Enum#valueOf(Class, String)} method works correctly
     * for all enum constants.
     */
    @Test
    void testValueOf() {
        assertEquals(PortClassEnum.APEX, PortClassEnum.valueOf("APEX"));
        assertEquals(PortClassEnum.ENTERPRISE, PortClassEnum.valueOf("ENTERPRISE"));
        assertEquals(PortClassEnum.METADATA, PortClassEnum.valueOf("METADATA"));
        assertEquals(PortClassEnum.PARTNER, PortClassEnum.valueOf("PARTNER"));
        assertEquals(PortClassEnum.TOOLING, PortClassEnum.valueOf("TOOLING"));
    }
}
