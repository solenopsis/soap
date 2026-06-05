package org.solenopsis.soap.port.factory;

import org.junit.jupiter.api.Test;
import org.solenopsis.soap.enterprise.Soap;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for {@link EnterprisePortFactory}.
 * <p>
 * Validates that the Enterprise port factory correctly creates Enterprise API port instances
 * using the CXF proxy factory.
 * </p>
 *
 * @author sfloess
 */
class EnterprisePortFactoryTest {

    /**
     * Validates that the factory's get() method creates a valid port instance.
     * This method uses the CXF proxy factory to create the port directly.
     */
    @Test
    void testGet() {
        EnterprisePortFactory factory = new EnterprisePortFactory();
        Soap port = factory.get();
        assertNotNull(port);
    }
}
