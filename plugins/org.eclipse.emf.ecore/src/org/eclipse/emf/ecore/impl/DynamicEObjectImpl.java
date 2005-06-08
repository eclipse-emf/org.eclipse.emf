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
 * $Id: DynamicEObjectImpl.java,v 1.3.2.1 2005/06/08 18:27:43 nickb Exp $
 */
package org.eclipse.emf.ecore.impl;


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;


/**
 * An implementation of the model object '<em><b>EObject</b></em>' that's tuned for dynamic use.
 */
public class DynamicEObjectImpl extends EObjectImpl implements EStructuralFeature.Internal.DynamicValueHolder
{
  /**
   * An internal class for holding less frequently members variables.
   */
  protected static class DynamicEPropertiesHolderImpl implements BasicEObjectImpl.EPropertiesHolder
  {
    protected URI eProxyURI;
    protected Resource.Internal eResource;
    protected EList eContents;
    protected EList eCrossReferences;

    public EClass getEClass()
    {
      throw new UnsupportedOperationException();
    }

    public void setEClass(EClass eClass)
    {
      throw new UnsupportedOperationException();
    }

    public URI getEProxyURI()
    {
      return eProxyURI;
    }

    public void setEProxyURI(URI eProxyURI)
    {
      this.eProxyURI = eProxyURI;
    }

    public Resource.Internal getEResource()
    {
      return eResource;
    }

    public void setEResource(Resource.Internal eResource)
    {
      this.eResource = eResource;
    }

    public EList getEContents()
    {
      return eContents;
    }

    public void setEContents(EList eContents)
    {
      this.eContents = eContents;
    }

    public EList getECrossReferences()
    {
      return eCrossReferences;
    }

    public void setECrossReferences(EList eCrossReferences)
    {
      this.eCrossReferences = eCrossReferences;
    }

    public boolean hasSettings()
    {
      throw new UnsupportedOperationException();
    }

    public void allocateSettings(int maximumDynamicFeatureID)
    {
      throw new UnsupportedOperationException();
    }

    public Object dynamicGet(int dynamicFeatureID)
    {
      throw new UnsupportedOperationException();
    }

    public void dynamicSet(int dynamicFeatureID, Object value)
    {
      throw new UnsupportedOperationException();
    }

    public void dynamicUnset(int dynamicFeatureID)
    {
      throw new UnsupportedOperationException();
    }
  }

  protected EClass eClass;
  protected Object [] eSettings;

  protected static final Object [] ENO_SETTINGS = new Object [0];

  /**
   * Creates a dynamic EObject.
   */
  public DynamicEObjectImpl()
  {
    super();
  }

  /**
   * Creates a dynamic EObject.
   */
  public DynamicEObjectImpl(EClass eClass) 
  {
    super();
    eSetClass(eClass);
  }

  protected int eStaticFeatureCount()
  {
    return 0;
  }

  public int eDerivedStructuralFeatureID(EStructuralFeature eStructuralFeature)
  {
    return eClass().getEAllStructuralFeatures().indexOf(eStructuralFeature);
  }

  protected BasicEObjectImpl.EPropertiesHolder eProperties()
  {
    if (eProperties == null)
    {
      eProperties = new DynamicEPropertiesHolderImpl();
    }
    return eProperties;
  }

  protected boolean eHasSettings()
  {
    return eSettings != null;
  }

  protected EStructuralFeature.Internal.DynamicValueHolder eSettings()
  {
    if (eSettings == null)
    {
      int size = eClass().getEAllStructuralFeatures().size() - eStaticFeatureCount();
      eSettings = size == 0 ? ENO_SETTINGS : new Object [size];
    }

    return this;
  }

  protected EClass eDynamicClass()
  {
    return eClass;
  }

  public EClass eClass()
  {
    return eClass;
  }

  public void eSetClass(EClass eClass)
  {
    this.eClass = eClass;
  }

  public Object dynamicGet(int dynamicFeatureID)
  {
    return eSettings[dynamicFeatureID];
  }

  public void dynamicSet(int dynamicFeatureID, Object value)
  {
    eSettings[dynamicFeatureID] = value;
  }

  public void dynamicUnset(int dynamicFeatureID)
  {
    eSettings[dynamicFeatureID] = null;
  }

/*
  public String toString()
  {
    String result = super.toString();
    int index = result.indexOf("DynamicEObjectImpl");
    return index == -1 ? result : result.substring(0, index) + result.substring(index + 7);
  }
*/
}
