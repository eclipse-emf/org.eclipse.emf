/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: FlatEObjectImpl.java,v 1.1 2006/05/10 21:56:07 emerks Exp $
 */
package org.eclipse.emf.ecore.impl;


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.ECrossReferenceEList;


/**
 * An implementation of the model object '<em><b>EObject</b></em>'.
 * This implementation flattens the fields for storing 
 * the {@link #eProxyURI}, the {@link #eContents}, and the {@link #eCrossReferences},
 * which in {@link EObjectImpl} are stored in the properties holder.
 * This reduces the likelihood of needing to allocate a properties holder and speeds up the access to these fields.
 */
public class FlatEObjectImpl extends EObjectImpl
{
  protected URI eProxyURI;
  protected EList eContents;
  protected EList eCrossReferences;

  /**
   * Creates a fast EObject.
   */
  protected FlatEObjectImpl() 
  {
    super();
  }

  protected EPropertiesHolder eProperties()
  {
    if (eProperties == null)
    {
      eProperties = new EPropertiesHolderBaseImpl();
    }
    return eProperties;
  }

  public boolean eIsProxy()
  {
    return eProxyURI != null;
  }
  
  public URI eProxyURI()
  {
    return eProxyURI;
  }

  public void eSetProxyURI(URI uri)
  {
    eProxyURI = uri;
  }

  public EList eContents()
  {
    if (eContents == null)
    {
      eContents = new EContentsEList(this);
    }
    return eContents;
  }

  public EList eCrossReferences()
  {
    if (eCrossReferences == null)
    {
      eCrossReferences = new ECrossReferenceEList(this);
    }
    return eCrossReferences;
  }
}
