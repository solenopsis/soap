package org.solenopsis.soap.port.factory;

import org.junit.jupiter.api.Test;
import org.solenopsis.soap.partner.SforceService;
import org.solenopsis.soap.partner.Soap;
import org.solenopsis.soap.service.factory.ServiceFactoryEnum;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for {@link PartnerPortFactory}.
 * <p>
 * Validates that the Partner port factory correctly creates Partner API port instances
 * from both service instances and via the factory's get() method.
 * </p>
 *
 * @author sfloess
 */
class PartnerPortFactoryTest {

    /**
     * Validates that the factory's get() method creates a valid port instance.
     */
    @Test
    void testGet() {
        PartnerPortFactory factory = new PartnerPortFactory();
        Soap port = factory.get();
        assertNotNull(port);
    }

    /**
     * Validates that creating a port from a Partner SforceService instance works correctly.
     */
    @Test
    void testCreatePortFromService() {
        SforceService service = (SforceService) ServiceFactoryEnum.PARTNER.createService();
        Soap port = service.getSoap();
        assertNotNull(port);
    }
}
