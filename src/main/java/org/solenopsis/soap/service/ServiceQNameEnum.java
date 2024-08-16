package org.solenopsis.soap.service;

import javax.xml.namespace.QName;
import org.flossware.commons.util.SoapUtil;
import org.solenopsis.soap.apex.ApexService;
import org.solenopsis.soap.enterprise.SforceService;
import org.solenopsis.soap.metadata.MetadataService;
import org.solenopsis.soap.tooling.SforceServiceService;

/**
 * Convenience email holding all builtin web service QNames.
 *
 * @author sfloess
 */
public enum ServiceQNameEnum {
    APEX(ApexService.class),
    ENTERPRISE(SforceService.class),
    METADATA(MetadataService.class),
    PARTNER(org.solenopsis.soap.partner.SforceService.class),
    TOOLING(SforceServiceService.class),
    ;

    private final QName qname;

    private ServiceQNameEnum(final Class klass) {
        qname = SoapUtil.computeQName(klass);
    }

    public QName getQName() {
        return qname;
    }
}
