package org.solenopsis.soap.port.factory;

import org.junit.jupiter.api.Test;
import org.solenopsis.soap.apex.ApexPortType;
import org.solenopsis.soap.apex.ApexService;
import org.solenopsis.soap.service.factory.ServiceFactoryEnum;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for {@link ApexPortFactory}.
 * <p>
 * Validates that the Apex port factory correctly creates Apex API port instances
 * from both service instances and via the factory's get() method.
 * </p>
 *
 * @author sfloess
 */
class ApexPortFactoryTest {

    /**
     * Validates that the factory's get() method creates a valid port instance.
     */
    @Test
    void testGet() {
        ApexPortFactory factory = new ApexPortFactory();
        ApexPortType port = factory.get();
        assertNotNull(port);
    }

    /**
     * Validates that creating a port from an ApexService instance works correctly.
     */
    @Test
    void testCreatePortFromService() {
        ApexService service = (ApexService) ServiceFactoryEnum.APEX.createService();
        ApexPortType port = service.getApex();
        assertNotNull(port);
    }
}
