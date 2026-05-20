package org.solenopsis.soap.port;

import org.junit.jupiter.api.Test;
import org.solenopsis.soap.apex.ApexPortType;
import org.solenopsis.soap.enterprise.Soap;
import org.solenopsis.soap.metadata.MetadataPortType;
import org.solenopsis.soap.tooling.SforceServicePortType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PortClassEnumTest {

    @Test
    void testAllEnumValues() {
        assertEquals(5, PortClassEnum.values().length);
    }

    @Test
    void testApexPortType() {
        assertNotNull(PortClassEnum.APEX);
        assertEquals(ApexPortType.class, PortClassEnum.APEX.getPortType());
    }

    @Test
    void testEnterprisePortType() {
        assertNotNull(PortClassEnum.ENTERPRISE);
        assertEquals(Soap.class, PortClassEnum.ENTERPRISE.getPortType());
    }

    @Test
    void testMetadataPortType() {
        assertNotNull(PortClassEnum.METADATA);
        assertEquals(MetadataPortType.class, PortClassEnum.METADATA.getPortType());
    }

    @Test
    void testPartnerPortType() {
        assertNotNull(PortClassEnum.PARTNER);
        assertEquals(org.solenopsis.soap.partner.Soap.class, PortClassEnum.PARTNER.getPortType());
    }

    @Test
    void testToolingPortType() {
        assertNotNull(PortClassEnum.TOOLING);
        assertEquals(SforceServicePortType.class, PortClassEnum.TOOLING.getPortType());
    }

    @Test
    void testValueOf() {
        assertEquals(PortClassEnum.APEX, PortClassEnum.valueOf("APEX"));
        assertEquals(PortClassEnum.ENTERPRISE, PortClassEnum.valueOf("ENTERPRISE"));
        assertEquals(PortClassEnum.METADATA, PortClassEnum.valueOf("METADATA"));
        assertEquals(PortClassEnum.PARTNER, PortClassEnum.valueOf("PARTNER"));
        assertEquals(PortClassEnum.TOOLING, PortClassEnum.valueOf("TOOLING"));
    }
}
