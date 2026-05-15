package org.solenopsis.soap.service;

import org.junit.jupiter.api.Test;
import org.solenopsis.soap.service.factory.ServiceFactoryEnum;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ServiceEnumTest {

    @Test
    void testAllEnumValues() {
        assertEquals(5, ServiceEnum.values().length);
    }

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

    @Test
    void testValueOf() {
        assertEquals(ServiceEnum.APEX, ServiceEnum.valueOf("APEX"));
        assertEquals(ServiceEnum.ENTERPRISE, ServiceEnum.valueOf("ENTERPRISE"));
        assertEquals(ServiceEnum.METADATA, ServiceEnum.valueOf("METADATA"));
        assertEquals(ServiceEnum.PARTNER, ServiceEnum.valueOf("PARTNER"));
        assertEquals(ServiceEnum.TOOLING, ServiceEnum.valueOf("TOOLING"));
    }
}
