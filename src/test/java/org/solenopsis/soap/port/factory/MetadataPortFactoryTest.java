package org.solenopsis.soap.port.factory;

import org.junit.jupiter.api.Test;
import org.solenopsis.soap.metadata.MetadataPortType;
import org.solenopsis.soap.metadata.MetadataService;
import org.solenopsis.soap.service.factory.ServiceFactoryEnum;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for {@link MetadataPortFactory}.
 * <p>
 * Validates that the Metadata port factory correctly creates Metadata API port instances
 * from both service instances and via the factory's get() method.
 * </p>
 *
 * @author sfloess
 */
class MetadataPortFactoryTest {

    /**
     * Validates that the factory's get() method creates a valid port instance.
     */
    @Test
    void testGet() {
        MetadataPortFactory factory = new MetadataPortFactory();
        MetadataPortType port = factory.get();
        assertNotNull(port);
    }

    /**
     * Validates that creating a port from a MetadataService instance works correctly.
     */
    @Test
    void testCreatePortFromService() {
        MetadataService service = (MetadataService) ServiceFactoryEnum.METADATA.createService();
        MetadataPortType port = service.getMetadata();
        assertNotNull(port);
    }
}
