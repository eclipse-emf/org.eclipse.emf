/**
 * <copyright>
 *
 * Copyright (c) 2005-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XMLOptions.java,v 1.4 2007/06/15 21:57:58 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi;


import java.util.Map;

import org.eclipse.emf.common.util.URI;


/**
 * This interface allows user specify various XML deserialization and serialization options
 */
public interface XMLOptions
{
  /**
   * This options allows the user to load and deserialize arbitrary XML (i.e. XML for which schema is not specified).
   * The default is <code>false</code>, unless set to <code>true</code> explicitly. 
   * To process schemaLocation/noNamespaceSchemaLocation attributes, user have to set 
   * {@link #setProcessSchemaLocations(boolean)} to <code>true</code>.
   * @see XMLResource#getEObjectToExtensionMap()
   * @param processAnyXML whether to process arbitrary XML.
   */
  void setProcessAnyXML(boolean processAnyXML);

  /**
   * @return processAnyXML value
   */
  boolean isProcessAnyXML();

  /**
   * This option allows the user to specify implementation of <code>EcoreBuilder</code> that will be used to process
   * schema locations to build Ecore dynamically.
   * If this option is not set and either 
   * {@link #getExternalSchemaLocations} is set or {@link #isProcessSchemaLocations()} returns <code>true</code> 
   * default <code>EcoreBuilder</code> will be created.
   * @see org.eclipse.emf.ecore.xmi.EcoreBuilder
   */
  void setEcoreBuilder(EcoreBuilder ecoreBuilder);

  /**
   * @return EcoreBuilder
   * @see org.eclipse.emf.ecore.xmi.EcoreBuilder
   */
  EcoreBuilder getEcoreBuilder();

  /**
   * The XML Schema Recommendation explicitly states that the inclusion of 
   * schemaLocation/noNamespaceSchemaLocation attributes is only a hint; 
   * it does not mandate that these attributes must be used to locate schemas. 
   * This option allows the user to specify schemas to use. 
   * If the targetNamespace of a schema (specified using this property) matches the targetNamespace 
   * of a schema occurring in the instance document in schemaLocation attribute, 
   * the schema specified by the user using this property will be used 
   * (i.e., the schemaLocation attribute in the instance document).   
   * @param schemaLocations - map of target namespace to schema location of type {@link org.eclipse.emf.common.util.URI}
   */
  void setExternalSchemaLocations(Map<String, URI> schemaLocations);

  /**
   * @return external schema locations
   */
  Map<String, URI> getExternalSchemaLocations();

  /**
   * This options allows the user to specify that the schemaLocation/noNamespaceSchemaLocation attributes 
   * occurring in the instance document will be processed to convert XSD(s) to Ecore file(s).
   * If this option is specified together with externalSchemaLocation option, the schemas specified in externalSchemaLocation
   * will take precedence.
   * @param processSchemaLocations
   */
  void setProcessSchemaLocations(boolean processSchemaLocations);

  /**
   * @return processSchemaLocations
   */
  boolean isProcessSchemaLocations();

}
