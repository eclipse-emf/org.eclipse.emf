/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: EObjectImpl.java,v 1.6 2005/04/12 20:03:13 emerks Exp $
 */
package org.eclipse.emf.ecore.impl;


import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EObject</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated NOT
 */
public class EObjectImpl extends BasicEObjectImpl implements EObject 
{
  /**
   * The bit of {@link #eFlags} that is used to represent {@link #eDeliver}.
   */
  protected static final int EDELIVER = 0x0001;
  
  /**
   * The bit of {@link #eFlags} that is used to represent whether there is a dynamic EClass.
   */
  protected static final int EDYNAMIC_CLASS = 0x0002;

  /**
   * The bit of {@link #eFlags} that is used to represent {@link #eIsProxy}.
   */
  protected static final int EPROXY = 0x004;

  /**
   * The last bit used by this class; derived classes may use bit values higher than this.
   */
  protected static final int ELAST_NOTIFIER_FLAG = EPROXY;

  /**
   * The last bit used by this class; derived classes may use bit values higher than this.
   */
  public static final int ELAST_EOBJECT_FLAG = ELAST_NOTIFIER_FLAG;

  /**
   * An extensible set of bit flags;
   * the first bit is used for {@link #EDELIVER} to implement {@link #eDeliver}
   * and the second bit is used for {@link #EPROXY} to implement {@link #eIsProxy}.
   */
  protected int eFlags = EDELIVER;

  /**
   * The list of {@link org.eclipse.emf.common.notify.Adapter}s associated with the notifier.
   */
  protected BasicEList eAdapters;

  /**
   * The container of this object.
   */
  protected InternalEObject eContainer;

  /**
   * The feature ID of this object's container holding feature, if there is one,
   * or {@link #EOPPOSITE_FEATURE_BASE EOPPOSITE_FEATURE_BASE} minus the feature ID of the container's feature that contains this object.
   */
  protected int eContainerFeatureID;

  /**
   * Additional less frequently used fields.
   */
  protected EPropertiesHolder eProperties;
  
  /**
   * <!-- begin-user-doc -->
   * Creates an EObject.
   * <!-- end-user-doc -->
   * @generated
   */
  protected EObjectImpl() 
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return EcorePackage.eINSTANCE.getEObject();
  }

  /*
   * Javadoc copied from interface.
   */
  public EList eAdapters()
  {
    if (eAdapters == null)
    {
      eAdapters =  new EAdapterList(this);
    }
    return eAdapters;
  }

  protected BasicEList eBasicAdapters()
  {
    return eAdapters;
  }

  /*
   * Javadoc copied from interface.
   */
  public boolean eDeliver()
  {
    return (eFlags & EDELIVER) != 0;
  }

  /*
   * Javadoc copied from interface.
   */
  public void eSetDeliver(boolean deliver)
  {
    if (deliver)
    {
      eFlags |= EDELIVER;
    }
    else
    {
      eFlags &= ~EDELIVER;
    }
  }

  /* 
   * @see org.eclipse.emf.ecore.EObject#eIsProxy()
   */
  public boolean eIsProxy()
  {
    return (eFlags & EPROXY) != 0;
  }
  
  /* 
   * @see org.eclipse.emf.ecore.InternalEObject#eSetProxyURI(org.eclipse.emf.common.util.URI)
   */
  public void eSetProxyURI(URI uri)
  {
    super.eSetProxyURI(uri);
    if (uri != null)
    {
      eFlags |= EPROXY;
    }
    else
    {
      eFlags &= ~EPROXY;
    }
  }
  
  protected EPropertiesHolder eProperties()
  {
    if (eProperties == null)
    {
      eProperties = new EPropertiesHolderImpl();
    }
    return eProperties;
  }

  protected EPropertiesHolder eBasicProperties()
  {
    return eProperties;
  }

  protected InternalEObject eInternalContainer()
  {
    return eContainer;
  }

  public int eContainerFeatureID()
  {
    return eContainerFeatureID;
  }

  protected void eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID)
  {
    eContainer = newContainer;
    eContainerFeatureID = newContainerFeatureID;
  }

  public EClass eClass()
  {
    return (eFlags & EDYNAMIC_CLASS) == 0 ? eStaticClass() : eProperties().getEClass();
  }

  public void eSetClass(EClass eClass)
  {
    super.eSetClass(eClass);
    if (eClass != null)
    {
      eFlags |= EDYNAMIC_CLASS;
    }
    else
    {
      eFlags &= ~EDYNAMIC_CLASS;
    }
  }
}
