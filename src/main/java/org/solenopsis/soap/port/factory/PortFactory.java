package org.solenopsis.soap.port.factory;

import java.util.function.Supplier;

/**
 * Factory API for creating a service port type.  Not sure this is overkill by
 * defining a marker interface that extends Supplier.
 *
 * @author sfloess
 */
public interface PortFactory<T> extends Supplier<T> {
}
