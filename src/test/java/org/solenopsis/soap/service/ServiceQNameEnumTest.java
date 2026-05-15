package org.solenopsis.soap.service;

import org.junit.jupiter.api.Test;
import javax.xml.namespace.QName;
import static org.junit.jupiter.api.Assertions.*;

class ServiceQNameEnumTest {

    @Test
    void testAllEnumValues() {
        assertEquals(5, ServiceQNameEnum.values().length);
    }

    @Test
    void testApexQName() {
        QName qname = ServiceQNameEnum.APEX.getQName();
        assertNotNull(qname);
        assertNotNull(qname.getNamespaceURI());
        assertNotNull(qname.getLocalPart());
    }

    @Test
    void testEnterpriseQName() {
        QName qname = ServiceQNameEnum.ENTERPRISE.getQName();
        assertNotNull(qname);
        assertNotNull(qname.getNamespaceURI());
        assertNotNull(qname.getLocalPart());
    }

    @Test
    void testMetadataQName() {
        QName qname = ServiceQNameEnum.METADATA.getQName();
        assertNotNull(qname);
        assertNotNull(qname.getNamespaceURI());
        assertNotNull(qname.getLocalPart());
    }

    @Test
    void testPartnerQName() {
        QName qname = ServiceQNameEnum.PARTNER.getQName();
        assertNotNull(qname);
        assertNotNull(qname.getNamespaceURI());
        assertNotNull(qname.getLocalPart());
    }

    @Test
    void testToolingQName() {
        QName qname = ServiceQNameEnum.TOOLING.getQName();
        assertNotNull(qname);
        assertNotNull(qname.getNamespaceURI());
        assertNotNull(qname.getLocalPart());
    }

    @Test
    void testQNamesAreUnique() {
        QName apex = ServiceQNameEnum.APEX.getQName();
        QName enterprise = ServiceQNameEnum.ENTERPRISE.getQName();
        QName metadata = ServiceQNameEnum.METADATA.getQName();
        QName partner = ServiceQNameEnum.PARTNER.getQName();
        QName tooling = ServiceQNameEnum.TOOLING.getQName();

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
}
