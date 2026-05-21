package org.solenopsis.soap.service.factory;

import jakarta.xml.ws.Service;
import java.util.function.Supplier;

/**
 * Factory interface for creating SOAP service instances.
 * <p>
 * This interface extends {@link Supplier} to provide a functional interface
 * for service creation. Implementations create JAX-WS {@link Service} instances
 * that are initialized with the appropriate WSDL URLs.
 * </p>
 * <p>
 * Services created by these factories are the top-level SOAP service objects
 * from which port instances can be obtained.
 * </p>
 *
 * @param <T> the type of JAX-WS Service this factory creates
 * @author sfloess
 */
public interface ServiceFactory<T extends Service> extends Supplier<T> {

}