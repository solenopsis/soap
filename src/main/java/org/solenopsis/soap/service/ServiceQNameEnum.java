package org.solenopsis.soap.service;

import com.sforce.soap.enterprise.SforceService;
import com.sforce.soap.tooling.SforceServiceService;
import javax.xml.namespace.QName;
import org.flossware.commons.util.SoapUtil;
import org.solenopsis.soap.apex.ApexService;
import org.solenopsis.soap.metadata.MetadataService;

/**
 * Convenience email holding all builtin web service QNames.
 *
 * @author sfloess
 */
public enum ServiceQNameEnum {
    APEX(ApexService.class),
    ENTERPRISE(SforceService.class),
    METADATA(MetadataService.class),
    PARTNER(com.sforce.soap.partner.SforceService.class),
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
