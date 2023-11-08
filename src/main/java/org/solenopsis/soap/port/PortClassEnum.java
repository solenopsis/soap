package org.solenopsis.soap.port;

import org.solenopsis.soap.apex.ApexPortType;
import org.solenopsis.soap.enterprise.Soap;
import org.solenopsis.soap.metadata.MetadataPortType;
import org.solenopsis.soap.tooling.SforceServicePortType;


/**
 * Convenience enum that holds all port type for builtin web services.
 *
 * @author sfloess
 */
public enum PortClassEnum {
    APEX(ApexPortType.class),
    ENTERPRISE(Soap.class),
    PARTNER(org.solenopsis.soap.partner.Soap.class),
    METADATA(MetadataPortType.class),
    TOOLING(SforceServicePortType.class);

    final Class portType;

    PortClassEnum(final Class portType) {
        this.portType = portType;
    }

    public Class getPortType() {
        return portType;
    }
}
