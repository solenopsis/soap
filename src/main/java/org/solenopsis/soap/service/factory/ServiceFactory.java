package org.solenopsis.soap.service.factory;

import jakarta.xml.ws.Service;
import java.util.function.Supplier;

/**
 * A service factory for creating service types.  Not sure this is overkill
 * by defining a marker interface that extends Supplier.
 *
 * @author sfloess
 */
public interface ServiceFactory<T extends Service> extends Supplier<T> {

}