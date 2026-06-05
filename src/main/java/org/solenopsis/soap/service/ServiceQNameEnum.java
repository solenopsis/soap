package org.solenopsis.soap.service;

import javax.xml.namespace.QName;
import org.flossware.jcommons.util.SoapUtil;
import org.solenopsis.soap.apex.ApexService;
import org.solenopsis.soap.enterprise.SforceService;
import org.solenopsis.soap.metadata.MetadataService;
import org.solenopsis.soap.tooling.SforceServiceService;

/**
 * Convenience enum holding all built-in Salesforce web service QNames.
 * <p>
 * A {@link QName} (Qualified Name) is an XML namespace-aware name consisting of
 * a namespace URI and a local part. This enum automatically computes the QName
 * for each Salesforce service class using reflection.
 * </p>
 *
 * @author sfloess
 */
public enum ServiceQNameEnum {
    /** Apex API service QName. */
    APEX(ApexService.class),

    /** Enterprise API service QName. */
    ENTERPRISE(SforceService.class),

    /** Metadata API service QName. */
    METADATA(MetadataService.class),

    /** Partner API service QName. */
    PARTNER(org.solenopsis.soap.partner.SforceService.class),

    /** Tooling API service QName. */
    TOOLING(SforceServiceService.class),
    ;

    /** The computed QName for this service. */
    private final QName qname;

    /**
     * Constructs a ServiceQNameEnum by computing the QName from the service class.
     * <p>
     * Uses {@link SoapUtil#computeQName(Class)} to extract the namespace and local
     * name from the service class's WSDL annotations.
     * </p>
     *
     * @param klass the service class to compute the QName from
     * @throws IllegalStateException if QName cannot be computed from the service class
     */
    private ServiceQNameEnum(final Class klass) {
        qname = SoapUtil.computeQName(klass);
        if (qname == null) {
            throw new IllegalStateException("Failed to compute QName for service class: " + klass);
        }
    }

    /**
     * Returns the computed {@link QName} for this service.
     * <p>
     * The QName contains the XML namespace URI and local part that identifies
     * this service in SOAP messages.
     * </p>
     *
     * @return the QName for this service (never null)
     */
    public QName getQName() {
        return qname;
    }
}
