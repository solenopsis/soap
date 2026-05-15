package org.solenopsis.soap.port.factory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PortFactoryEnumTest {

    @Test
    void testAllEnumValues() {
        assertEquals(5, PortFactoryEnum.values().length);
    }

    @Test
    void testApexFactory() {
        assertNotNull(PortFactoryEnum.APEX);
        assertNotNull(PortFactoryEnum.APEX.getPortFactory());
    }

    @Test
    void testEnterpriseFactory() {
        assertNotNull(PortFactoryEnum.ENTERPRISE);
        assertNotNull(PortFactoryEnum.ENTERPRISE.getPortFactory());
    }

    @Test
    void testMetadataFactory() {
        assertNotNull(PortFactoryEnum.METADATA);
        assertNotNull(PortFactoryEnum.METADATA.getPortFactory());
    }

    @Test
    void testPartnerFactory() {
        assertNotNull(PortFactoryEnum.PARTNER);
        assertNotNull(PortFactoryEnum.PARTNER.getPortFactory());
    }

    @Test
    void testToolingFactory() {
        assertNotNull(PortFactoryEnum.TOOLING);
        assertNotNull(PortFactoryEnum.TOOLING.getPortFactory());
    }

    @Test
    void testCreatePort() {
        assertNotNull(PortFactoryEnum.METADATA.createPort());
    }

    @Test
    void testCreatePortWithUrl() {
        Object port = PortFactoryEnum.METADATA.createPort("https://test.salesforce.com/services/Soap/m/58.0");
        assertNotNull(port);
    }

    @Test
    void testCreatePortWithNullUrl() {
        assertThrows(IllegalArgumentException.class, () ->
            PortFactoryEnum.METADATA.createPort(null));
    }

    @Test
    void testCreatePortWithEmptyUrl() {
        assertThrows(IllegalArgumentException.class, () ->
            PortFactoryEnum.METADATA.createPort(""));
    }

    @Test
    void testCreatePortWithBlankUrl() {
        assertThrows(IllegalArgumentException.class, () ->
            PortFactoryEnum.METADATA.createPort("   "));
    }
}
