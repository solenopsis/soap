/**
 * Enumerations for managing SOAP port types and metadata.
 * <p>
 * This package provides convenient enumerations that aggregate port-related
 * metadata for Salesforce SOAP APIs. These enums simplify working with port
 * classes, port creation methods, and other port-related information.
 * </p>
 * <p>
 * Key classes:
 * </p>
 * <ul>
 *   <li>{@link org.solenopsis.soap.port.PortClassEnum} - Maps service types to their port interface classes</li>
 *   <li>{@link org.solenopsis.soap.port.PortMethodEnum} - Discovers and stores WebEndpoint-annotated port creation methods</li>
 * </ul>
 * <p>
 * Example usage:
 * </p>
 * <pre>
 * // Get the port class for a specific API
 * Class&lt;?&gt; portClass = PortClassEnum.METADATA.getPortType();
 *
 * // Get the port creation method
 * Method method = PortMethodEnum.APEX.getPortMethod();
 * </pre>
 *
 * @see org.solenopsis.soap.port.factory
 * @author sfloess
 */
package org.solenopsis.soap.port;
