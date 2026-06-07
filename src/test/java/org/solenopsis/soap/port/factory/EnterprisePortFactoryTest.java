package org.solenopsis.soap.port.factory;

import org.junit.jupiter.api.Test;
import org.solenopsis.soap.enterprise.Soap;
import org.solenopsis.soap.enterprise.SforceService;
import org.solenopsis.soap.service.factory.ServiceFactoryEnum;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for {@link EnterprisePortFactory}.
 * <p>
 * Validates that the Enterprise port factory correctly creates Enterprise API port instances
 * from both service instances and via the factory's get() method.
 * </p>
 *
 * @author sfloess
 */
class EnterprisePortFactoryTest {

    /**
     * Validates that the factory's get() method creates a valid port instance.
     */
    @Test
    void testGet() {
        EnterprisePortFactory factory = new EnterprisePortFactory();
        Soap port = factory.get();
        assertNotNull(port);
    }

    /**
     * Validates that creating a port from a SforceService instance works correctly.
     */
    @Test
    void testCreatePortFromService() {
        SforceService service = (SforceService) ServiceFactoryEnum.ENTERPRISE.createService();
        Soap port = service.getSoap();
        assertNotNull(port);
    }
}
