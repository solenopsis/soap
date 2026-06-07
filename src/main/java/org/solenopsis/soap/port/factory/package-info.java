/**
 * Factory classes and enumerations for creating SOAP port instances.
 * <p>
 * This package provides a factory pattern implementation for creating SOAP port
 * instances for each Salesforce API. The main entry point is {@link PortFactoryEnum},
 * which provides a unified interface for port creation across all supported APIs.
 * </p>
 * <p>
 * Key classes:
 * <ul>
 *   <li>{@link org.solenopsis.soap.port.factory.PortFactoryEnum} - Main factory enum for creating ports</li>
 *   <li>{@link org.solenopsis.soap.port.factory.PortFactory} - Interface for port factory implementations</li>
 * </ul>
 * </p>
 * <p>
 * Example usage:
 * <pre>
 * // Create a port with default endpoint
 * MetadataPortType port = PortFactoryEnum.METADATA.createPort();
 *
 * // Create a port with custom endpoint URL
 * MetadataPortType port = PortFactoryEnum.METADATA.createPort(
 *     "https://na1.salesforce.com/services/Soap/m/58.0"
 * );
 * </pre>
 * </p>
 * <p>
 * Each API has its own internal factory implementation (ApexPortFactory,
 * EnterprisePortFactory, etc.) that handles the specifics of creating that
 * API's port type. These implementations are package-private and accessed
 * through the {@link PortFactoryEnum}.
 * </p>
 *
 * @see org.solenopsis.soap.service.factory
 * @author sfloess
 */
package org.solenopsis.soap.port.factory;
