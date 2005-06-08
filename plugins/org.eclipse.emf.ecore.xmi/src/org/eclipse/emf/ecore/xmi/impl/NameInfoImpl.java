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
 * $Id: NameInfoImpl.java,v 1.2 2005/06/08 06:16:07 nickb Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;

import org.eclipse.emf.ecore.xmi.NameInfo;


/**
 * Implementation of the {@link NameInfo} interface.
 */
public class NameInfoImpl implements NameInfo
{
  protected String localPart;
  protected String qualifiedName;
  protected String namespaceURI;
  
  public String getLocalPart()
  {
    return localPart;
  }
  
  public String getNamespaceURI()
  {
    return namespaceURI;
  }

  public String getQualifiedName()
  {
    return qualifiedName;
  }
  
  public void setLocalPart(String name)
  {
    this.localPart = name;
  }
  
  public void setNamespaceURI(String uri)
  {
    this.namespaceURI = uri;
  }
 
  public void setQualifiedName(String name)
  {
    this.qualifiedName = name;
  }
  
}
