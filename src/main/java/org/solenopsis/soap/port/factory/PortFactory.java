package org.solenopsis.soap.port.factory;

import java.util.function.Supplier;

/**
 * Factory interface for creating SOAP service port instances.
 * <p>
 * This interface extends {@link Supplier} to provide a functional interface
 * for port creation. Implementations should provide the logic to create and
 * configure specific Salesforce API port types.
 * </p>
 * <p>
 * The generic type {@code T} represents the specific port interface type
 * (e.g., {@code MetadataPortType}, {@code ApexPortType}, etc.).
 * </p>
 *
 * @param <T> the type of SOAP port this factory creates
 * @author sfloess
 */
public interface PortFactory<T> extends Supplier<T> {
}
