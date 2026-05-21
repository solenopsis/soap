package org.solenopsis.soap.port.factory;

import org.junit.jupiter.api.Test;
import org.solenopsis.soap.service.factory.ServiceFactoryEnum;
import org.solenopsis.soap.tooling.SforceServicePortType;
import org.solenopsis.soap.tooling.SforceServiceService;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for {@link ToolingPortFactory}.
 * <p>
 * Validates that the Tooling port factory correctly creates Tooling API port instances
 * from both service instances and via the factory's get() method.
 * </p>
 *
 * @author sfloess
 */
class ToolingPortFactoryTest {

    /**
     * Validates that creating a port from a Tooling SforceServiceService instance works correctly.
     */
    @Test
    void testCreatePortFromService() {
        ToolingPortFactory factory = new ToolingPortFactory();
        SforceServiceService service = ServiceFactoryEnum.TOOLING.createService();
        SforceServicePortType port = factory.createPort(service);
        assertNotNull(port);
    }

    /**
     * Validates that the factory's get() method creates a valid port instance.
     */
    @Test
    void testGet() {
        ToolingPortFactory factory = new ToolingPortFactory();
        SforceServicePortType port = factory.get();
        assertNotNull(port);
    }
}
