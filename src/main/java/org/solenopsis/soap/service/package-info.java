/**
 * Enumerations for managing SOAP service metadata and configuration.
 * <p>
 * This package provides enumerations that encapsulate key metadata about
 * Salesforce SOAP services, including WSDL URLs, QNames, and aggregated
 * service information. These enums simplify service configuration and
 * initialization.
 * </p>
 * <p>
 * Key classes:
 * </p>
 * <ul>
 *   <li>{@link org.solenopsis.soap.service.ServiceEnum} - Aggregates all service metadata (QName, WSDL, Factory)</li>
 *   <li>{@link org.solenopsis.soap.service.ServiceWsdlEnum} - Provides access to WSDL file URLs from classpath</li>
 *   <li>{@link org.solenopsis.soap.service.ServiceQNameEnum} - Computes and stores service QNames</li>
 * </ul>
 * <p>
 * Example usage:
 * </p>
 * <pre>
 * // Get WSDL URL for a service
 * URL wsdl = ServiceWsdlEnum.METADATA.getUrl();
 *
 * // Get QName for a service
 * QName qname = ServiceQNameEnum.APEX.getQName();
 *
 * // Get complete service information
 * ServiceEnum service = ServiceEnum.ENTERPRISE;
 * ServiceWsdlEnum wsdlEnum = service.getWsdl();
 * ServiceQNameEnum qnameEnum = service.getQName();
 * </pre>
 *
 * @see org.solenopsis.soap.service.factory
 * @author sfloess
 */
package org.solenopsis.soap.service;
