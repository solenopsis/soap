package org.flossware.jaxws.utils;

import jakarta.xml.ws.BindingProvider;

/**
 *
 * @author sfloess
 */
public final class UrlUtils {
    private UrlUtils() {
    }

    public static <T> T setUrl(final T port, final String url) {
        ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);

        return port;
    }

}
