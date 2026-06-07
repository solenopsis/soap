/**
 * Factory classes and enumerations for creating JAX-WS Service instances.
 * <p>
 * This package implements the factory pattern for creating JAX-WS Service objects
 * for Salesforce SOAP APIs. Services are top-level SOAP objects that are initialized
 * with WSDL files and used to create port instances for making SOAP calls.
 * </p>
 * <p>
 * Key classes:
 * <ul>
 *   <li>{@link org.solenopsis.soap.service.factory.ServiceFactoryEnum} - Main factory enum for creating services</li>
 *   <li>{@link org.solenopsis.soap.service.factory.ServiceFactory} - Interface for service factory implementations</li>
 * </ul>
 * </p>
 * <p>
 * Example usage:
 * <pre>
 * // Create a service instance
 * MetadataService service = (MetadataService) ServiceFactoryEnum.METADATA.createService();
 *
 * // Get a port from the service
 * MetadataPortType port = service.getMetadata();
 * </pre>
 * </p>
 * <p>
 * <strong>Performance Note:</strong> Service instances are cached as singletons.
 * The factory uses thread-safe lazy initialization (double-checked locking) to
 * create services only once and reuse them across all calls. This improves
 * performance by avoiding repeated WSDL parsing.
 * </p>
 * <p>
 * Each API has its own internal factory implementation (ApexServiceFactory,
 * EnterpriseServiceFactory, etc.) that handles the singleton creation pattern.
 * These implementations are package-private and accessed through the
 * {@link ServiceFactoryEnum}.
 * </p>
 *
 * @see org.solenopsis.soap.port.factory
 * @author sfloess
 */
package org.solenopsis.soap.service.factory;
