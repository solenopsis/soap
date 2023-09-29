package org.solenopsis.soap.cxf.port;

import org.solenopsis.soap.cxf.apex.ApexPortType;
import org.solenopsis.soap.cxf.enterprise.Soap;
import org.solenopsis.soap.cxf.metadata.MetadataPortType;
import org.solenopsis.soap.cxf.tooling.SforceServicePortType;


/**
 * Convenience enum that holds all port type for builtin web services.
 *
 * @author sfloess
 */
public enum PortClassEnum {
    APEX(ApexPortType.class),
    ENTERPRISE(Soap.class),
    PARTNER(org.solenopsis.soap.cxf.partner.Soap.class),
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
