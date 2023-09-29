package org.solenopsis.soap.wsimport.port;

import org.solenopsis.soap.wsimport.apex.ApexPortType;
import org.solenopsis.soap.wsimport.metadata.MetadataPortType;
import org.solenopsis.soap.wsimport.partner.Soap;
import org.solenopsis.soap.wsimport.tooling.SforceServicePortType;



/**
 * Convenience enum that holds all port type for builtin web services.
 *
 * @author sfloess
 */
public enum PortClassEnum {
    APEX(ApexPortType.class),
    ENTERPRISE(Soap.class),
    PARTNER(org.solenopsis.soap.wsimport.partner.Soap.class),
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
