/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: EcoreBuilder.java,v 1.2 2005/12/20 17:16:35 elena Exp $
 */
package org.eclipse.emf.ecore.xmi;


import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.ExtendedMetaData;


/**
 * The interface describes an XML Schema to Ecore builder. 
 *
 */
public interface EcoreBuilder
{
  /**
   * Given an XML schema location URI this method creates corresponding Ecore model(s)
   * @param uri - location of the XML Schema files.
   * @return Collection of EPackage(s)
   * @throws Exception
   * @see org.eclipse.emf.common.util.URI
   */
  public Collection generate(URI uri) throws Exception;

  /**
   * Given XML Schema location URIs this method creates corresponding Ecore model(s)
   * @param uris - locations of the XML Schema files.
   * @return Collection of EPackage(s)
   * @throws Exception
   * @see org.eclipse.emf.common.util.URI
   */
  public Collection generate(Collection uris) throws Exception;

  /**
   * Given a map of XML Schema targetNamespaces (String) to XML Schema location URIs, this method
   * generates corresponding Ecore model(s).
   * @param targetNamespaceToURI - a map of XML Schema targetNamespaces to XML Schema location URIs
   * @return Collection of EPackage(s)
   * @throws Exception
   * @see org.eclipse.emf.common.util.URI
   */
  public Collection generate(Map targetNamespaceToURI) throws Exception;
  
  /**
   * Sets extended meta data to register generated Ecore models.
   * Note the same extended meta data should be used for loading/saving an instance document.
   * @param extendedMetaData
   */
  public void setExtendedMetaData(ExtendedMetaData extendedMetaData);

}
