package org.solenopsis.soap.service;

import org.junit.jupiter.api.Test;
import javax.xml.namespace.QName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for {@link ServiceQNameEnum}.
 * <p>
 * Validates that all QName enum constants correctly compute and provide XML
 * qualified names for each Salesforce API service.
 * </p>
 *
 * @author sfloess
 */
class ServiceQNameEnumTest {

    /**
     * Verifies that all expected enum constants are present.
     * Should have exactly 5 values for the 5 Salesforce APIs.
     */
    @Test
    void testAllEnumValues() {
        assertEquals(5, ServiceQNameEnum.values().length);
    }

    /**
     * Validates that the APEX QName is correctly computed with namespace URI and local part.
     */
    @Test
    void testApexQName() {
        QName qname = ServiceQNameEnum.APEX.getQName();
        assertNotNull(qname);
        assertNotNull(qname.getNamespaceURI());
        assertNotNull(qname.getLocalPart());
    }

    /**
     * Validates that the ENTERPRISE QName is correctly computed with namespace URI and local part.
     */
    @Test
    void testEnterpriseQName() {
        QName qname = ServiceQNameEnum.ENTERPRISE.getQName();
        assertNotNull(qname);
        assertNotNull(qname.getNamespaceURI());
        assertNotNull(qname.getLocalPart());
    }

    /**
     * Validates that the METADATA QName is correctly computed with namespace URI and local part.
     */
    @Test
    void testMetadataQName() {
        QName qname = ServiceQNameEnum.METADATA.getQName();
        assertNotNull(qname);
        assertNotNull(qname.getNamespaceURI());
        assertNotNull(qname.getLocalPart());
    }

    /**
     * Validates that the PARTNER QName is correctly computed with namespace URI and local part.
     */
    @Test
    void testPartnerQName() {
        QName qname = ServiceQNameEnum.PARTNER.getQName();
        assertNotNull(qname);
        assertNotNull(qname.getNamespaceURI());
        assertNotNull(qname.getLocalPart());
    }

    /**
     * Validates that the TOOLING QName is correctly computed with namespace URI and local part.
     */
    @Test
    void testToolingQName() {
        QName qname = ServiceQNameEnum.TOOLING.getQName();
        assertNotNull(qname);
        assertNotNull(qname.getNamespaceURI());
        assertNotNull(qname.getLocalPart());
    }

    /**
     * Validates that all QNames are unique across the different APIs.
     * Each API should have its own distinct qualified name.
     */
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
