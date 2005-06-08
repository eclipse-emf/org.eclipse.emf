/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: XMIResourceImpl.java,v 1.1.2.1 2005/06/08 18:27:42 nickb Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLSave;

/**
 * This class represents an XMIResource.
 */
public class XMIResourceImpl extends XMLResourceImpl implements XMIResource {

  /**
   * Constructor for XMIResourceImpl.
   */
  public XMIResourceImpl() {
    super();
  }

  /**
   * Constructor for XMIResourceImpl.
   * @param uri
   */
  public XMIResourceImpl(URI uri) {
    super(uri);
  }

  protected XMLHelper createXMLHelper()
  {
  	return new XMIHelperImpl(this);
  }
  
  protected XMLLoad createXMLLoad()
  {
  	return new XMILoadImpl(createXMLHelper());
  }		
  
  protected XMLSave createXMLSave()
  {
  	return new XMISaveImpl(createXMLHelper());
  }	
}
