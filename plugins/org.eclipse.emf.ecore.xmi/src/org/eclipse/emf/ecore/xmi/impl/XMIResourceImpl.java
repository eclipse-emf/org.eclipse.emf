/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: XMIResourceImpl.java,v 1.5 2006/12/05 20:23:28 emerks Exp $
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
public class XMIResourceImpl extends XMLResourceImpl implements XMIResource
{

  String version = XMIResource.VERSION_VALUE;
  
  /**
   * Constructor for XMIResourceImpl.
   */
  public XMIResourceImpl()
  {
    super();
  }

  /**
   * Constructor for XMIResourceImpl.
   * @param uri
   */
  public XMIResourceImpl(URI uri)
  {
    super(uri);
  }

  @Override
  protected XMLHelper createXMLHelper()
  {
    return new XMIHelperImpl(this);
  }

  @Override
  protected XMLLoad createXMLLoad()
  {
    return new XMILoadImpl(createXMLHelper());
  }

  @Override
  protected XMLSave createXMLSave()
  {
    return new XMISaveImpl(createXMLHelper());
  }
  
  @Override
  protected boolean useIDs()
  {
    return true;
  }

  public String getXMIVersion()
  {
    return version;
  }

  public void setXMIVersion(String version)
  {
    this.version = version;
  }

  public String getXMINamespace()
  {
    if (version.equals(XMIResource.VERSION_VALUE))
    {
      return XMIResource.XMI_URI;
    }
    else
    {
      return XMIResource.XMI_NAMESPACE_PREFIX + version;
    }
  }

  public void setXMINamespace(String namespace)
  {
    if (namespace.startsWith(XMIResource.XMI_NAMESPACE_PREFIX))
    {
      this.setXMIVersion(namespace.substring(XMIResource.XMI_NAMESPACE_PREFIX.length()));
    } 
    else if (namespace.equals(XMIResource.XMI_URI))
    {
      this.setXMIVersion(XMIResource.VERSION_VALUE);
    }
    else
    {
      // TODO translation
      throw new IllegalArgumentException("Invalid XMI namespace: '"+namespace+"'");
    }
    
  }
  
}
